package com.example.barber.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GestionLiquidaciones {

    private List<LiquidacionBarbero> liquidaciones;

    public GestionLiquidaciones() {
        this.liquidaciones = new ArrayList<>();
    }

    // ----------------------------------------------------------------
    // CREAR
    // ----------------------------------------------------------------
    /**
     * Crea y registra una liquidación calculando el pago final automáticamente.
     *
     * @param barbero El barbero a liquidar.
     * @param totalProducido Total facturado por el barbero en el período.
     * @param porcentajeComision Porcentaje de comisión (ej: 60.0 para 60%).
     * @return La liquidación creada.
     * @throws IllegalArgumentException si algún parámetro es inválido.
     */
    public LiquidacionBarbero crearLiquidacion(Usuario barbero,
            double totalProducido,
            double porcentajeComision) {
        validarParametros(barbero, totalProducido, porcentajeComision);

        double pagoFinal = calcularPagoFinal(totalProducido, porcentajeComision);
        LiquidacionBarbero liquidacion = new LiquidacionBarbero(
                barbero, totalProducido, porcentajeComision, pagoFinal);

        liquidaciones.add(liquidacion);
        return liquidacion;
    }

    // ----------------------------------------------------------------
    // LEER
    // ----------------------------------------------------------------
    /**
     * Retorna todas las liquidaciones registradas.
     */
    public List<LiquidacionBarbero> obtenerTodas() {
        return new ArrayList<>(liquidaciones);
    }

    /**
     * Busca todas las liquidaciones de un barbero específico.
     *
     * @param barbero El barbero a buscar.
     * @return Lista de liquidaciones del barbero (puede estar vacía).
     */
    public List<LiquidacionBarbero> obtenerPorBarbero(Usuario barbero) {
        if (barbero == null) {
            throw new IllegalArgumentException("El barbero no puede ser nulo.");
        }
        List<LiquidacionBarbero> resultado = new ArrayList<>();
        for (LiquidacionBarbero liq : liquidaciones) {
            if (liq.getBarbero() != null && liq.getBarbero().equals(barbero)) {
                resultado.add(liq);
            }
        }
        return resultado;
    }

    /**
     * Retorna la primera liquidación de un barbero, si existe.
     */
    public Optional<LiquidacionBarbero> obtenerPrimeraPorBarbero(Usuario barbero) {
        return obtenerPorBarbero(barbero).stream().findFirst();
    }

    // ----------------------------------------------------------------
    // ACTUALIZAR
    // ----------------------------------------------------------------
    /**
     * Actualiza el total producido y recalcula el pago final de una
     * liquidación.
     *
     * @param liquidacion La liquidación a actualizar.
     * @param nuevoTotal Nuevo total producido.
     * @return La liquidación actualizada.
     */
    public LiquidacionBarbero actualizarTotalProducido(LiquidacionBarbero liquidacion,
            double nuevoTotal) {
        if (liquidacion == null) {
            throw new IllegalArgumentException("La liquidación no puede ser nula.");
        }
        if (nuevoTotal < 0) {
            throw new IllegalArgumentException("El total producido no puede ser negativo.");
        }
        liquidacion.setTotalProducido(nuevoTotal);
        liquidacion.setPagoFinal(
                calcularPagoFinal(nuevoTotal, liquidacion.getPorcentajeComision()));
        return liquidacion;
    }

    /**
     * Actualiza el porcentaje de comisión y recalcula el pago final.
     *
     * @param liquidacion La liquidación a actualizar.
     * @param nuevoPorcentaje Nuevo porcentaje de comisión (0–100).
     * @return La liquidación actualizada.
     */
    public LiquidacionBarbero actualizarPorcentajeComision(LiquidacionBarbero liquidacion,
            double nuevoPorcentaje) {
        if (liquidacion == null) {
            throw new IllegalArgumentException("La liquidación no puede ser nula.");
        }
        if (nuevoPorcentaje < 0 || nuevoPorcentaje > 100) {
            throw new IllegalArgumentException("El porcentaje debe estar entre 0 y 100.");
        }
        liquidacion.setPorcentajeComision(nuevoPorcentaje);
        liquidacion.setPagoFinal(
                calcularPagoFinal(liquidacion.getTotalProducido(), nuevoPorcentaje));
        return liquidacion;
    }

    // ----------------------------------------------------------------
    // ELIMINAR
    // ----------------------------------------------------------------
    /**
     * Elimina una liquidación específica de la lista.
     *
     * @param liquidacion La liquidación a eliminar.
     * @return true si fue eliminada, false si no se encontraba.
     */
    public boolean eliminarLiquidacion(LiquidacionBarbero liquidacion) {
        return liquidaciones.remove(liquidacion);
    }

    /**
     * Elimina todas las liquidaciones de un barbero.
     *
     * @param barbero El barbero cuyas liquidaciones se eliminarán.
     * @return Número de liquidaciones eliminadas.
     */
    public int eliminarPorBarbero(Usuario barbero) {
        List<LiquidacionBarbero> aEliminar = obtenerPorBarbero(barbero);
        liquidaciones.removeAll(aEliminar);
        return aEliminar.size();
    }

    // ----------------------------------------------------------------
    // CÁLCULOS Y REPORTES
    // ----------------------------------------------------------------
    /**
     * Calcula el pago final dado un total producido y un porcentaje de
     * comisión.
     */
    public double calcularPagoFinal(double totalProducido, double porcentajeComision) {
        return totalProducido * (porcentajeComision / 100.0);
    }

    /**
     * Retorna la suma de todos los pagos finales registrados.
     */
    public double totalPagosRealizados() {
        double total = 0;
        for (LiquidacionBarbero liq : liquidaciones) {
            total += liq.getPagoFinal();
        }
        return total;
    }

    /**
     * Retorna la suma de pagos finales de un barbero específico.
     */
    public double totalPagadoABarbero(Usuario barbero) {
        double total = 0;
        for (LiquidacionBarbero liq : obtenerPorBarbero(barbero)) {
            total += liq.getPagoFinal();
        }
        return total;
    }

    /**
     * Retorna la liquidación con el mayor pago final, si existe.
     */
    public Optional<LiquidacionBarbero> liquidacionMayorPago() {
        return liquidaciones.stream()
                .max((a, b) -> Double.compare(a.getPagoFinal(), b.getPagoFinal()));
    }

    // ----------------------------------------------------------------
    // VALIDACIÓN PRIVADA
    // ----------------------------------------------------------------
    private void validarParametros(Usuario barbero,
            double totalProducido,
            double porcentajeComision) {
        if (barbero == null) {
            throw new IllegalArgumentException("El barbero no puede ser nulo.");
        }
        if (totalProducido < 0) {
            throw new IllegalArgumentException("El total producido no puede ser negativo.");
        }
        if (porcentajeComision < 0 || porcentajeComision > 100) {
            throw new IllegalArgumentException("El porcentaje de comisión debe estar entre 0 y 100.");
        }
    }
}
