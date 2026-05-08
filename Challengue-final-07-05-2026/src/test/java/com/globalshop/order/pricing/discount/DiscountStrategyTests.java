package com.globalshop.order.pricing.discount;

import com.globalshop.order.config.PricingConstants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Estrategias de Descuento Promocional")
class DiscountStrategyTests {

    private static final double PRECIO_BASE = 100.0;

    @Test
    @DisplayName("SAVE10 descuenta 10%")
    void testDescuentoSave10() {
        DiscountStrategy descuento = new Save10Discount();
        assertEquals(PRECIO_BASE * (1.0 - PricingConstants.PROMO_AHORRO10), descuento.aplicarDescuento(PRECIO_BASE), 0.01);
        assertEquals("SAVE10", descuento.getCodigo());
    }

    @Test
    @DisplayName("SAVE20 descuenta 20%")
    void testDescuentoSave20() {
        DiscountStrategy descuento = new Save20Discount();
        assertEquals(PRECIO_BASE * (1.0 - PricingConstants.PROMO_AHORRO20), descuento.aplicarDescuento(PRECIO_BASE), 0.01);
    }

    @Test
    @DisplayName("BLACKFRIDAY descuenta 30%")
    void testDescuentoBlackFriday() {
        DiscountStrategy descuento = new BlackFridayDiscount();
        assertEquals(PRECIO_BASE * (1.0 - PricingConstants.PROMO_VIERNES_NEGRO), descuento.aplicarDescuento(PRECIO_BASE), 0.01);
    }

    @Test
    @DisplayName("WELCOME descuenta 15%")
    void testDescuentoBienvenida() {
        DiscountStrategy descuento = new WelcomeDiscount();
        assertEquals(PRECIO_BASE * (1.0 - PricingConstants.PROMO_BIENVENIDA), descuento.aplicarDescuento(PRECIO_BASE), 0.01);
    }

    @Test
    @DisplayName("NoDiscount no modifica el precio")
    void testSinDescuento() {
        DiscountStrategy descuento = new NoDiscount();
        assertEquals(PRECIO_BASE, descuento.aplicarDescuento(PRECIO_BASE), 0.01);
        assertEquals("NONE", descuento.getCodigo());
    }
}
