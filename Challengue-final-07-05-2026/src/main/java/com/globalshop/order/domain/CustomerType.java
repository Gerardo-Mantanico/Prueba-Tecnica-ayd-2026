package com.globalshop.order.domain;

public enum CustomerType {
    RETAIL("Minorista"),
    WHOLESALE("Mayorista"),
    INTERNATIONAL("Internacional");

    private final String descripcion;

    CustomerType(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
