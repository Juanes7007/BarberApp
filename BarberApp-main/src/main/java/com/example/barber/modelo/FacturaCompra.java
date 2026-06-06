package com.example.barber.modelo;

import java.time.LocalDate;

public class FacturaCompra {

    private String id;

    private Proveedor proveedor;

    private LocalDate fecha;

    private double total;

    public FacturaCompra() {
    }

    public FacturaCompra(String id,
                         Proveedor proveedor,
                         LocalDate fecha,
                         double total) {

        this.id = id;
        this.proveedor = proveedor;
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

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
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
