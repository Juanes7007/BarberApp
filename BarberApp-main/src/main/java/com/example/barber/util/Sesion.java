package com.example.barber.util;

import com.example.barber.modelo.Usuario;

public class Sesion {

    private static Usuario usuarioActual;

    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public static void setUsuarioActual(
            Usuario usuarioActual) {

        Sesion.usuarioActual = usuarioActual;
    }
}
