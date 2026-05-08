package com.globalshop.order.pricing.strategy;

import com.globalshop.order.domain.*;
import com.globalshop.order.config.PricingConstants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Estrategias de Precio")
class PricingStrategyTests {

    @Test
    @DisplayName("STANDARD + RETAIL + Premium aplica 10% descuento")
    void testStandardMinoristaPremium() {
        Order orden = new OrderBuilder(100.0, OrderType.STANDARD, CustomerType.RETAIL)
            .conPremium(true)
            .construir();

        double precio = new StandardOrderStrategy().calcularPrecio(orden);

        assertEquals(100.0 * (1.0 - PricingConstants.DESC_PREMIUM_MINORISTA), precio, 0.01);
    }

    @Test
    @DisplayName("STANDARD + WHOLESALE >$1000 aplica 15% descuento")
    void testStandardMayoristaGrande() {
        Order orden = new OrderBuilder(1500.0, OrderType.STANDARD, CustomerType.WHOLESALE)
            .construir();

        double precio = new StandardOrderStrategy().calcularPrecio(orden);

        assertEquals(1500.0 * (1.0 - PricingConstants.DESC_MAYORISTA_GRANDE), precio, 0.01);
    }

    @Test
    @DisplayName("STANDARD + INTERNATIONAL + fin de semana aplica recargo 20% + 5%")
    void testStandardInternacionalConFindeSemana() {
        Order orden = new OrderBuilder(100.0, OrderType.STANDARD, CustomerType.INTERNATIONAL)
            .conFindeSemana(true)
            .construir();

        double precio = new StandardOrderStrategy().calcularPrecio(orden);

        assertEquals(100.0 * PricingConstants.RECARGO_INTERNACIONAL * PricingConstants.RECARGO_FIN_SEMANA, precio, 0.01);
    }

    @Test
    @DisplayName("EXPRESS siempre aplica recargo del 30%")
    void testExpressRecargoBase() {
        Order orden = new OrderBuilder(100.0, OrderType.EXPRESS, CustomerType.RETAIL)
            .construir();

        double precio = new ExpressOrderStrategy().calcularPrecio(orden);

        assertEquals(100.0 * PricingConstants.RECARGO_EXPRES, precio, 0.01);
    }

    @Test
    @DisplayName("BULK $1000 aplica 5% descuento")
    void testBulkMontoPequeno() {
        Order orden = new OrderBuilder(1000.0, OrderType.BULK, CustomerType.RETAIL)
            .construir();

        double precio = new BulkOrderStrategy().calcularPrecio(orden);

        assertEquals(1000.0 * (1.0 - PricingConstants.DESC_VOLUMEN_PEQUENO), precio, 0.01);
    }

    @Test
    @DisplayName("BULK $6000 aplica 25% descuento")
    void testBulkMontoGrande() {
        Order orden = new OrderBuilder(6000.0, OrderType.BULK, CustomerType.RETAIL)
            .construir();

        double precio = new BulkOrderStrategy().calcularPrecio(orden);

        assertEquals(6000.0 * (1.0 - PricingConstants.DESC_VOLUMEN_GRANDE), precio, 0.01);
    }
}
