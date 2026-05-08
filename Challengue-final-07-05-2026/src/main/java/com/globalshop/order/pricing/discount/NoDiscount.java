package com.globalshop.order.pricing.discount;

public class NoDiscount implements DiscountStrategy {

    @Override
    public double aplicarDescuento(double precioActual) {
        return precioActual;
    }

    @Override
    public String getCodigo() {
        return "NONE";
    }

    @Override
    public double getPorcentaje() {
        return 0.0;
    }
}
