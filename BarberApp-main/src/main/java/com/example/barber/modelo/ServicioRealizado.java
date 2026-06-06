package com.example.barber.modelo;

import java.time.LocalDate;

public class ServicioRealizado {

    private String id;

    private Cliente cliente;

    private Usuario barbero;

    private Servicio servicio;

    private LocalDate fecha;

    private double valor;

    public ServicioRealizado() {
    }

    public ServicioRealizado(String id,
            Cliente cliente,
            Usuario barbero,
            Servicio servicio,
            LocalDate fecha,
            double valor) {

        this.id = id;
        this.cliente = cliente;
        this.barbero = barbero;
        this.servicio = servicio;
        this.fecha = fecha;
        this.valor = valor;
    }

    // getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getBarbero() {
        return barbero;
    }

    public void setBarbero(Usuario barbero) {
        this.barbero = barbero;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
