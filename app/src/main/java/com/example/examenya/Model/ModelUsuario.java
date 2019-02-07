package com.example.examenya.Model;

public class ModelUsuario {
    public static String getNombre_usuario() {
        return nombre_usuario;
    }

    public static void setNombre_usuario(String nombre_usuario) {
        ModelUsuario.nombre_usuario = nombre_usuario;
    }

    private static String nombre_usuario;
}
