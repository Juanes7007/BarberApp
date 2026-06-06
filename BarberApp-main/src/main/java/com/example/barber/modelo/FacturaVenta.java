
package com.example.barber.modelo;

import java.time.LocalDate;

public class FacturaVenta {

    private String id;

    private Cliente cliente;

    private LocalDate fecha;

    private double total;

    public FacturaVenta() {
    }

    public FacturaVenta(String id,
                        Cliente cliente,
                        LocalDate fecha,
                        double total) {

        this.id = id;
        this.cliente = cliente;
        this.fecha = fecha;
        this.total = total;
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}