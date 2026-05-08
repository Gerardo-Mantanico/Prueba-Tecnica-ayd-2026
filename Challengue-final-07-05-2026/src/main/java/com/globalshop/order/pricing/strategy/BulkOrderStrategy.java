package com.globalshop.order.pricing.strategy;

import com.globalshop.order.domain.Order;
import com.globalshop.order.config.PricingConstants;

public class BulkOrderStrategy implements PricingStrategy {

    @Override
    public double calcularPrecio(Order orden) {
        double monto = orden.getMontoBase();
        return monto * PricingConstants.factorDescuentoVolumen(monto);
    }

    @Override
    public String getNombre() {
        return "BulkOrderStrategy";
    }
}
