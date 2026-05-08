package com.globalshop.order.pricing;

import com.globalshop.order.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Facade de Calculo de Precios")
class PriceCalculationFacadeTests {

    private PriceCalculationFacade facade;

    @BeforeEach
    void inicializar() {
        facade = new PriceCalculationFacade();
    }

    @Test
    @DisplayName("STANDARD + premium sin codigo = 10% descuento")
    void testCalcularSinDescuento() {
        Order orden = new OrderBuilder(100.0, OrderType.STANDARD, CustomerType.RETAIL)
            .conPremium(true).construir();

        assertEquals(90.0, facade.calcularPrecioFinal(orden), 0.01);
    }

    @Test
    @DisplayName("STANDARD + premium + SAVE10 = 10% + 10% descuento")
    void testCalcularConDescuento() {
        Order orden = new OrderBuilder(100.0, OrderType.STANDARD, CustomerType.RETAIL)
            .conPremium(true).conCodigo("SAVE10").construir();

        assertEquals(81.0, facade.calcularPrecioFinal(orden), 0.01);
    }

    @Test
    @DisplayName("Codigo invalido es ignorado (aplica NoDiscount)")
    void testCodigoInvalidoIgnorado() {
        Order orden = new OrderBuilder(100.0, OrderType.STANDARD, CustomerType.RETAIL)
            .conPremium(true).conCodigo("INVENTADO").construir();

        assertEquals(90.0, facade.calcularPrecioFinal(orden), 0.01);
    }

    @Test
    @DisplayName("EXPRESS + WHOLESALE + BLACKFRIDAY combina recargo y descuentos")
    void testEscenarioComplejo() {
        Order orden = new OrderBuilder(1000.0, OrderType.EXPRESS, CustomerType.WHOLESALE)
            .conCodigo("BLACKFRIDAY").construir();

        double esperado = 1000.0 * 1.30 * 0.92 * 0.70;
        assertEquals(esperado, facade.calcularPrecioFinal(orden), 0.01);
    }

    @Test
    @DisplayName("Las tres estrategias estan registradas y funcionan")
    void testTodasEstrategiasDisponibles() {
        assertDoesNotThrow(() -> facade.calcularPrecioFinal(
            new OrderBuilder(100.0, OrderType.STANDARD, CustomerType.RETAIL).construir()));
        assertDoesNotThrow(() -> facade.calcularPrecioFinal(
            new OrderBuilder(100.0, OrderType.EXPRESS, CustomerType.RETAIL).construir()));
        assertDoesNotThrow(() -> facade.calcularPrecioFinal(
            new OrderBuilder(100.0, OrderType.BULK, CustomerType.RETAIL).construir()));
    }
}
