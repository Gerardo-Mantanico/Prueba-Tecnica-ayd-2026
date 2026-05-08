package com.globalshop.order.pricing.strategy;

import com.globalshop.order.domain.Order;

public interface PricingStrategy {

    double calcularPrecio(Order orden);

    String getNombre();
}
