package com.globalshop.order.domain;

import com.globalshop.order.config.PricingConstants;
import java.time.LocalDateTime;
import java.util.Objects;

public final class Order {

    private final double montoBase;
    private final OrderType tipoOrden;
    private final CustomerType tipoCliente;
    private final boolean premium;
    private final boolean findeSemana;
    private final String codigoDescuento;
    private final LocalDateTime fechaCreacion;

    private double precioFinal;
    private String estado = "PENDIENTE";

    Order(double montoBase, OrderType tipoOrden, CustomerType tipoCliente,
          boolean premium, boolean findeSemana, String codigoDescuento) {

        this.montoBase = validarMonto(montoBase);
        this.tipoOrden = Objects.requireNonNull(tipoOrden, "tipoOrden no puede ser null");
        this.tipoCliente = Objects.requireNonNull(tipoCliente, "tipoCliente no puede ser null");
        this.premium = premium;
        this.findeSemana = findeSemana;
        this.codigoDescuento = codigoDescuento;
        this.fechaCreacion = LocalDateTime.now();
        this.precioFinal = montoBase;
    }

    private double validarMonto(double monto) {
        if (monto < PricingConstants.MONTO_MINIMO) {
            throw new IllegalArgumentException(
                "El monto debe ser >= " + PricingConstants.MONTO_MINIMO
            );
        }
        if (monto > PricingConstants.MONTO_MAXIMO) {
            throw new IllegalArgumentException(
                "El monto excede el máximo permitido: " + PricingConstants.MONTO_MAXIMO
            );
        }
        return monto;
    }

    public double getMontoBase() {
        return montoBase;
    }

    public OrderType getTipoOrden() {
        return tipoOrden;
    }

    public CustomerType getTipoCliente() {
        return tipoCliente;
    }

    public boolean esPremium() {
        return premium;
    }

    public boolean esFindeSemana() {
        return findeSemana;
    }

    public String getCodigoDescuento() {
        return codigoDescuento;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public double getPrecioFinal() {
        return precioFinal;
    }

    public String getEstado() {
        return estado;
    }

    public void setPrecioFinal(double precioFinal) {
        this.precioFinal = precioFinal;
    }

    public void setEstado(String estado) {
        this.estado = Objects.requireNonNull(estado);
    }

    public double calcularAhorro() {
        return montoBase - precioFinal;
    }

    public double getPorcentajeDescuento() {
        if (montoBase == 0) return 0;
        return (calcularAhorro() / montoBase) * 100;
    }

    @Override
    public String toString() {
        return String.format(
            "Order{tipo=%s, cliente=%s, monto_base=Q%.2f, monto_final=Q%.2f, " +
            "descuento=%s, premium=%s, fin_de_semana=%s}",
            tipoOrden, tipoCliente, montoBase, precioFinal,
            codigoDescuento != null ? codigoDescuento : "NONE",
            premium, findeSemana
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order orden = (Order) o;
        return Double.compare(orden.montoBase, montoBase) == 0 &&
               premium == orden.premium &&
               findeSemana == orden.findeSemana &&
               tipoOrden == orden.tipoOrden &&
               tipoCliente == orden.tipoCliente &&
               Objects.equals(codigoDescuento, orden.codigoDescuento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(montoBase, tipoOrden, tipoCliente, premium, findeSemana, codigoDescuento);
    }
}
