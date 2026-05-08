package com.globalshop.order.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Builder de Ordenes")
class OrderBuilderTests {

    @Test
    @DisplayName("Crea orden con solo los campos requeridos")
    void testConstructorMinimo() {
        Order orden = new OrderBuilder(100.0, OrderType.STANDARD, CustomerType.RETAIL)
            .construir();

        assertEquals(100.0, orden.getMontoBase());
        assertEquals(OrderType.STANDARD, orden.getTipoOrden());
        assertEquals(CustomerType.RETAIL, orden.getTipoCliente());
        assertFalse(orden.esPremium());
        assertNull(orden.getCodigoDescuento());
    }

    @Test
    @DisplayName("Crea orden con todos los campos opcionales")
    void testConstructorCompleto() {
        Order orden = new OrderBuilder(500.0, OrderType.EXPRESS, CustomerType.WHOLESALE)
            .conPremium(true)
            .conFindeSemana(true)
            .conCodigo("SAVE20")
            .construir();

        assertTrue(orden.esPremium());
        assertTrue(orden.esFindeSemana());
        assertEquals("SAVE20", orden.getCodigoDescuento());
    }

    @Test
    @DisplayName("Lanza excepcion si el monto es negativo")
    void testValidacionMontoMinimo() {
        assertThrows(IllegalArgumentException.class, () ->
            new OrderBuilder(-1.0, OrderType.STANDARD, CustomerType.RETAIL).construir()
        );
    }

    @Test
    @DisplayName("calcularAhorro retorna la diferencia entre monto base y final")
    void testCalcularAhorro() {
        Order orden = new OrderBuilder(100.0, OrderType.STANDARD, CustomerType.RETAIL).construir();
        orden.setPrecioFinal(80.0);

        assertEquals(20.0, orden.calcularAhorro(), 0.01);
    }

    @Test
    @DisplayName("Dos ordenes con los mismos datos son iguales")
    void testIgualdadOrdenes() {
        Order orden1 = new OrderBuilder(100.0, OrderType.STANDARD, CustomerType.RETAIL)
            .conPremium(true).conCodigo("SAVE10").construir();
        Order orden2 = new OrderBuilder(100.0, OrderType.STANDARD, CustomerType.RETAIL)
            .conPremium(true).conCodigo("SAVE10").construir();

        assertEquals(orden1, orden2);
        assertEquals(orden1.hashCode(), orden2.hashCode());
    }
}
