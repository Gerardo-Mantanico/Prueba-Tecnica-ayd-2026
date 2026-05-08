package com.globalshop.order.config;

public final class PricingConstants {

    private PricingConstants() {}

    public static final double DESC_PREMIUM_MINORISTA = 0.10;
    public static final double DESC_MAYORISTA_PEQUENO = 0.05;
    public static final double DESC_MAYORISTA_GRANDE = 0.15;
    public static final double UMBRAL_MAYORISTA = 1000.0;
    public static final double DESC_MAYORISTA_EXPRES = 0.08;
    public static final double RECARGO_INTERNACIONAL = 1.20;
    public static final double RECARGO_FIN_SEMANA = 1.05;
    public static final double DESC_VOLUMEN_PEQUENO = 0.05;
    public static final double DESC_VOLUMEN_MEDIANO = 0.15;
    public static final double DESC_VOLUMEN_GRANDE = 0.25;
    public static final double UMBRAL_VOLUMEN_MEDIANO = 2000.0;
    public static final double UMBRAL_VOLUMEN_GRANDE = 5000.0;
    public static final double RECARGO_EXPRES = 1.30;
    public static final double DESC_EXPRES_PREMIUM = 0.12;
    public static final double PROMO_AHORRO10 = 0.10;
    public static final double PROMO_AHORRO20 = 0.20;
    public static final double PROMO_VIERNES_NEGRO = 0.30;
    public static final double PROMO_BIENVENIDA = 0.15;
    public static final double MONTO_MINIMO = 0.0;
    public static final double MONTO_MAXIMO = 1_000_000.0;

    public static double factorDescuentoVolumen(double monto) {
        if (monto > UMBRAL_VOLUMEN_GRANDE) {
            return 1.0 - DESC_VOLUMEN_GRANDE;
        } else if (monto > UMBRAL_VOLUMEN_MEDIANO) {
            return 1.0 - DESC_VOLUMEN_MEDIANO;
        } else {
            return 1.0 - DESC_VOLUMEN_PEQUENO;
        }
    }

    public static double factorDescuentoMayorista(double monto) {
        if (monto >= UMBRAL_MAYORISTA) {
            return 1.0 - DESC_MAYORISTA_GRANDE;
        } else {
            return 1.0 - DESC_MAYORISTA_PEQUENO;
        }
    }
}
