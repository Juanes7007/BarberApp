package com.example.barber.modelo;

public class Usuario {
    private String id;
    private String username;
    private String password;
    private String tipo;
    private String correo;

    public Usuario() {
    }

    public Usuario(String id, String username, String password, String tipo, String correo) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.tipo = tipo;
        this.correo = correo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}