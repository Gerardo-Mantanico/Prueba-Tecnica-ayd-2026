package com.globalshop.order.pricing.discount;

import com.globalshop.order.config.PricingConstants;

public class BlackFridayDiscount implements DiscountStrategy {

    @Override
    public double aplicarDescuento(double precioActual) {
        return precioActual * (1.0 - PricingConstants.PROMO_VIERNES_NEGRO);
    }

    @Override
    public String getCodigo() {
        return "BLACKFRIDAY";
    }

    @Override
    public double getPorcentaje() {
        return PricingConstants.PROMO_VIERNES_NEGRO * 100;
    }
}
