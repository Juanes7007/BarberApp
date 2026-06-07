/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.barber.modelo;

import java.util.UUID;


/**
 *
 * @author DURLEY
 */
public class CitasDTO {

    private String hora;
    private String cliente;
    private String servicio;
    private String estado;
    private String barbero;
    private String fecha;
    private String id;

    public CitasDTO() {
    }

    public CitasDTO(String hora, String cliente, String servicio, String estado, String barbero, String fecha) {
        this.hora = hora;
        this.cliente = cliente;
        this.servicio = servicio;
        this.estado = estado;
        this.barbero = barbero;
        this.fecha = fecha;
        this.id = UUID.randomUUID().toString();
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getBarbero() {
        return barbero;
    }

    public void setBarbero(String barbero) {
        this.barbero = barbero;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
