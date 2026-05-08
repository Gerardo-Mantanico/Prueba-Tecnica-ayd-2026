package com.globalshop.order.pricing.discount;

import com.globalshop.order.config.PricingConstants;

public class WelcomeDiscount implements DiscountStrategy {

    @Override
    public double aplicarDescuento(double precioActual) {
        return precioActual * (1.0 - PricingConstants.PROMO_BIENVENIDA);
    }

    @Override
    public String getCodigo() {
        return "WELCOME";
    }

    @Override
    public double getPorcentaje() {
        return PricingConstants.PROMO_BIENVENIDA * 100;
    }
}
