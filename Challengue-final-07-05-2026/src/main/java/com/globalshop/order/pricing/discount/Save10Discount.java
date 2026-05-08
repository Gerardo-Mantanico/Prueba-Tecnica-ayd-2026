package com.globalshop.order.pricing.discount;

import com.globalshop.order.config.PricingConstants;

public class Save10Discount implements DiscountStrategy {

    @Override
    public double aplicarDescuento(double precioActual) {
        return precioActual * (1.0 - PricingConstants.PROMO_AHORRO10);
    }

    @Override
    public String getCodigo() {
        return "SAVE10";
    }

    @Override
    public double getPorcentaje() {
        return PricingConstants.PROMO_AHORRO10 * 100;
    }
}
