package com.globalshop.order.pricing.strategy;

import com.globalshop.order.domain.*;
import com.globalshop.order.config.PricingConstants;

public class ExpressOrderStrategy implements PricingStrategy {

    @Override
    public double calcularPrecio(Order orden) {
        double total = orden.getMontoBase() * PricingConstants.RECARGO_EXPRES;

        if (orden.getTipoCliente() == CustomerType.WHOLESALE) {
            total *= (1.0 - PricingConstants.DESC_MAYORISTA_EXPRES);
        }

        if (orden.esPremium()) {
            total *= (1.0 - PricingConstants.DESC_EXPRES_PREMIUM);
        }

        return total;
    }

    @Override
    public String getNombre() {
        return "ExpressOrderStrategy";
    }
}
