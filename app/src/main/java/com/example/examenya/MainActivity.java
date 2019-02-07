package com.example.examenya;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.examenya.Model.ModelUsuario;
import com.example.examenya.Productos.Productos;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import static com.example.examenya.Constantes.Constantes.KEY_PASSWORD;
import static com.example.examenya.Constantes.Constantes.KEY_USERNAME;
import static com.example.examenya.Constantes.Constantes.LOGIN_URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ModelUsuario modelUsuario = new ModelUsuario();
    private EditText editTextUsername;
    private EditText editTextPassword;
    private TextInputLayout tluser;
    private TextInputLayout tlpass;
    private Button buttonLogin;
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername = (EditText) findViewById(R.id.editTextUser);
        editTextPassword = (EditText) findViewById(R.id.editTextPass);
        tluser = (TextInputLayout) findViewById(R.id.tluser);
        tlpass = (TextInputLayout) findViewById(R.id.tlpass);
        buttonLogin = (Button) findViewById(R.id.btnEntrar);


        editTextUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                tluser.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                tluser.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    tluser.setError(null);
                }

            }
        });

        editTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                tlpass.setError(null);

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tlpass.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    tlpass.setError(null);
                }

            }
        });


        buttonLogin.setOnClickListener(this);

        SharedPreferences preferences = getSharedPreferences("temp", getApplicationContext().MODE_PRIVATE);
        String login_name = preferences.getString("username", "");


        if (login_name != "") {
            Intent i = new Intent(this, Productos.class);
            modelUsuario.setNombre_usuario(login_name);
            startActivity(i);
            finish();
        }
    }

    private void userLogin() {
        username = editTextUsername.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("Yournventory account is disabled."))
                            Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                        else if (response.trim().equals("Invalidn details supplied."))
                            Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                        else {
                            modelUsuario.setNombre_usuario(username);

                            SharedPreferences preferences = getSharedPreferences("temp", getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("username", modelUsuario.getNombre_usuario());

                            editor.commit();
                            openPrincipal();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put(KEY_USERNAME, username);
                map.put(KEY_PASSWORD, password);
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void openPrincipal() {
        Intent intent = new Intent(this, Principal.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

        userLogin();
        userLoginHardcode();


    }

    private void userLoginHardcode() {

        if (editTextUsername.getText().toString().equals("Luis") && editTextPassword.getText().toString().equals("password")) {
            Intent intent = new Intent(MainActivity.this, Productos.class);
            startActivity(intent);
        }
        if (!editTextUsername.getText().toString().equals("Luis")) {
            tluser.setError("Usuario incorrecto");
        }
        if (editTextUsername.getText().toString().isEmpty()) {
            tluser.setError("Campo vacio");
        }
        if (!editTextPassword.getText().toString().equals("password")) {
            tlpass.setError("Contraseña incorrecto");
        }
        if (editTextPassword.getText().toString().isEmpty()) {
            tlpass.setError("Campo vacio");
        }
    }
}
