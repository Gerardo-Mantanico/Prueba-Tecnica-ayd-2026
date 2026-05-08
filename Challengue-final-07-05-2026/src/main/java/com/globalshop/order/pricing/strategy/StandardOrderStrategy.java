package com.globalshop.order.pricing.strategy;

import com.globalshop.order.domain.*;
import com.globalshop.order.config.PricingConstants;

public class StandardOrderStrategy implements PricingStrategy {

    @Override
    public double calcularPrecio(Order orden) {
        double total = orden.getMontoBase();

        return switch (orden.getTipoCliente()) {
            case RETAIL -> calcularMinorista(total, orden.esPremium());
            case WHOLESALE -> calcularMayorista(total, orden.getMontoBase());
            case INTERNATIONAL -> calcularInternacional(total, orden.esFindeSemana());
        };
    }

    private double calcularMinorista(double monto, boolean esPremium) {
        if (esPremium) {
            return monto * (1.0 - PricingConstants.DESC_PREMIUM_MINORISTA);
        }
        return monto;
    }

    private double calcularMayorista(double monto, double montoBase) {
        return monto * PricingConstants.factorDescuentoMayorista(montoBase);
    }

    private double calcularInternacional(double monto, boolean esFindeSemana) {
        double total = monto * PricingConstants.RECARGO_INTERNACIONAL;
        if (esFindeSemana) {
            total *= PricingConstants.RECARGO_FIN_SEMANA;
        }
        return total;
    }

    @Override
    public String getNombre() {
        return "StandardOrderStrategy";
    }
}
