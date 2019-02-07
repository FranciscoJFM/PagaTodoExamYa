package com.example.examenya.Recarga;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.examenya.Productos.Productos;
import com.example.examenya.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RecargaActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextPhone;
    private EditText editTextCantidad;
    private TextInputLayout tlphone;
    private TextInputLayout tlcantidad;
    private Button buttonContinuar;
    private LinearLayout lyactionbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recarga);

        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        editTextCantidad = (EditText) findViewById(R.id.editTextCantidad);
        tlphone = (TextInputLayout) findViewById(R.id.tlphone);
        tlcantidad = (TextInputLayout) findViewById(R.id.tlcantidad);
        buttonContinuar = (Button) findViewById(R.id.btnContinuar);
        lyactionbar = (LinearLayout) findViewById(R.id.lyactionbar);

        lyactionbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecargaActivity.this, Productos.class);
                startActivity(intent);
            }
        });

        editTextPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                tlphone.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                tlphone.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    tlphone.setError(null);
                }

            }
        });

        editTextCantidad.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                tlcantidad.setError(null);

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tlcantidad.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    tlcantidad.setError(null);
                }

            }
        });


        buttonContinuar.setOnClickListener(this);

        SharedPreferences preferences = getSharedPreferences("temp", getApplicationContext().MODE_PRIVATE);


    }


    @Override
    public void onClick(View v) {


        userLoginHardcode(v);


    }

    private void userLoginHardcode(View v) {

        if (editTextPhone.getText().toString().equals("1234567890") && editTextCantidad.getText().toString().equals("100")) {

            AlertDialog.Builder mBuilder = new AlertDialog.Builder(RecargaActivity.this);
            mBuilder.setCancelable(false);

            View mView = getLayoutInflater().inflate(R.layout.fragment_dialog_recarga, null);

            Date d = new Date();

            TextView fechaCompleta = (TextView) mView.findViewById(R.id.tvFecha);
            SimpleDateFormat fecc = new SimpleDateFormat("dd'/'MMM '/' yy");
            String fechacComplString = fecc.format(d);
            fechaCompleta.setText(fechacComplString);

            TextView hora = (TextView) mView.findViewById(R.id.tvHora);
            SimpleDateFormat ho = new SimpleDateFormat(" HH:mm:ss");
            String horaString = ho.format(d);
            hora.setText(horaString);


            Button btnaceptar = (Button) mView.findViewById(R.id.btnaceptar);
            Button btncancelar = (Button) mView.findViewById(R.id.btncancelar);
            TextView telcel = (TextView) mView.findViewById(R.id.numtel);
            TextView mont = (TextView) mView.findViewById(R.id.monto);


            mBuilder.setView(mView);
            final AlertDialog timerDialog = mBuilder.create();


            btncancelar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    timerDialog.dismiss();


                }
            });

            btnaceptar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    timerDialog.dismiss();
                    AlertDialog.Builder mBuilder2 = new AlertDialog.Builder(RecargaActivity.this);
                    mBuilder2.setCancelable(false);

                    View mView = getLayoutInflater().inflate(R.layout.fragment_dialog_ok, null);


                    Button btnaceptarok = (Button) mView.findViewById(R.id.btnaceptarok);

                    mBuilder2.setView(mView);
                    final AlertDialog timerDialog2 = mBuilder2.create();

                    btnaceptarok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            timerDialog2.dismiss();
                            Intent intent = new Intent(getApplicationContext(), Productos.class);
                            getApplicationContext().startActivity(intent);

                        }
                    });

                    timerDialog2.show();
                }
            });


            timerDialog.show();
        }

        if (editTextPhone.getText().toString().isEmpty()) {
            tlphone.setError("Campo vacio");
        }

        if (editTextCantidad.getText().toString().isEmpty()) {
            tlcantidad.setError("Campo vacio");
        }
    }
}
