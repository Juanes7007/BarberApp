/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.barber.modelo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Admi(gay)
 */
public class Citas {

    private StringProperty hora;
    private StringProperty cliente;
    private StringProperty servicio;
    private StringProperty estado;
    private StringProperty barbero;
    private StringProperty fecha;

    public Citas(String hora, String cliente, String servicio, String estado, String barbero, String fecha) {
        this.hora = new SimpleStringProperty(hora);
        this.cliente = new SimpleStringProperty(cliente);
        this.servicio = new SimpleStringProperty(servicio);
        this.estado = new SimpleStringProperty(estado);
        this.barbero = new SimpleStringProperty(barbero);
        this.fecha = new SimpleStringProperty(fecha);
    }
    
    
public StringProperty horaProperty() {
    return hora;
}


public String getHora() {
    return hora.get();
}

public StringProperty clienteProperty() {
    return cliente;
}


public String getCliente() {
    return cliente.get();
}
public StringProperty servicioProperty() {
    return servicio;
}


public String getServicio() {
    return servicio.get();
}
public StringProperty estadoProperty() {
    return estado;
}


public String getEstado() {
    return estado.get();
}

public StringProperty barberoProperty() {
    return barbero;
}


public String getBarbero() {
    return barbero.get();
}

public StringProperty fechaProperty() {
    return fecha;
}


public String getFecha() {
    return fecha.get();
}
   
public void setHora(String hora) {
    this.hora.set(hora);
}
public void setCliente(String cliente) {
    this.cliente.set(cliente);
}
public void setServicio(String servicio) {
    this.servicio.set(servicio);
}
public void setEstado(String estado) {
    this.estado.set(estado);
}
public void setBarbero(String barbero) {
    this.barbero.set(barbero);
}
public void setFecha(String fecha) {
    this.fecha.set(fecha);
}
    
    
}