package com.globalshop.order.pricing.discount;

public interface DiscountStrategy {

    double aplicarDescuento(double precioActual);

    String getCodigo();

    double getPorcentaje();
}
