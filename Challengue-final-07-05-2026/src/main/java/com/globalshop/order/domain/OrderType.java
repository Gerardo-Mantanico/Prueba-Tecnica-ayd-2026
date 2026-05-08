package com.globalshop.order.domain;

public enum OrderType {
    STANDARD(0.0, "Envío estándar"),
    EXPRESS(0.3, "Envío express"),
    BULK(0.0, "Compra por volumen");

    private final double recargo;
    private final String descripcion;

    OrderType(double recargo, String descripcion) {
        this.recargo = recargo;
        this.descripcion = descripcion;
    }

    public double getRecargo() {
        return recargo;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
