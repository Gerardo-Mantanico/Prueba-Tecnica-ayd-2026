package com.globalshop.order.pricing.discount;

import com.globalshop.order.config.PricingConstants;

public class Save20Discount implements DiscountStrategy {

    @Override
    public double aplicarDescuento(double precioActual) {
        return precioActual * (1.0 - PricingConstants.PROMO_AHORRO20);
    }

    @Override
    public String getCodigo() {
        return "SAVE20";
    }

    @Override
    public double getPorcentaje() {
        return PricingConstants.PROMO_AHORRO20 * 100;
    }
}
