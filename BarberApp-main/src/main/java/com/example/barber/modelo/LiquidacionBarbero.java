package com.example.barber.modelo;

public class LiquidacionBarbero {

    private Usuario barbero;

    private double totalProducido;

    private double porcentajeComision;

    private double pagoFinal;

    public LiquidacionBarbero() {
    }

    public LiquidacionBarbero(Usuario barbero,
            double totalProducido,
            double porcentajeComision,
            double pagoFinal) {

        this.barbero = barbero;
        this.totalProducido = totalProducido;
        this.porcentajeComision = porcentajeComision;
        this.pagoFinal = pagoFinal;
    }

    // getters y setters
    public Usuario getBarbero() {
        return barbero;
    }

    public void setBarbero(Usuario barbero) {
        this.barbero = barbero;
    }

    public double getTotalProducido() {
        return totalProducido;
    }

    public void setTotalProducido(double totalProducido) {
        this.totalProducido = totalProducido;
    }

    public double getPorcentajeComision() {
        return porcentajeComision;
    }

    public void setPorcentajeComision(double porcentajeComision) {
        this.porcentajeComision = porcentajeComision;
    }

    public double getPagoFinal() {
        return pagoFinal;
    }

    public void setPagoFinal(double pagoFinal) {
        this.pagoFinal = pagoFinal;
    }
}
