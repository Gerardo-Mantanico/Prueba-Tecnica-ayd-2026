package com.globalshop.order.integration;

import com.globalshop.order.domain.*;
import com.globalshop.order.pricing.PriceCalculationFacade;
import com.globalshop.order.service.OrderProcessingService;
import com.globalshop.order.command.OrderCommandProcessor;
import com.globalshop.order.notification.impl.NoOpNotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Integracion del Sistema de Ordenes")
class OrderProcessingIntegrationTests {

    private OrderProcessingService servicio;

    @BeforeEach
    void inicializar() {
        servicio = new OrderProcessingService(
            new PriceCalculationFacade(),
            new NoOpNotificationService()
        );
    }

    @Test
    @DisplayName("Procesa orden STANDARD: ejecuta precio y notificacion")
    void testIntegracionOrdenStandard() {
        Order orden = new OrderBuilder(100.0, OrderType.STANDARD, CustomerType.RETAIL)
            .conPremium(true).conCodigo("SAVE10").construir();

        OrderCommandProcessor.ReporteProcesamiento reporte =
            servicio.procesarOrden(orden, "cliente@test.com");

        assertTrue(reporte.fueExitoso());
        assertEquals(2, reporte.getResultados().size());
    }

    @Test
    @DisplayName("BULK $6000 + BLACKFRIDAY = precio final correcto")
    void testIntegracionOrdenBulk() {
        Order orden = new OrderBuilder(6000.0, OrderType.BULK, CustomerType.RETAIL)
            .conCodigo("BLACKFRIDAY").construir();

        servicio.procesarOrden(orden, "bulk@test.com");

        assertEquals(3150.0, orden.getPrecioFinal(), 1.0);
    }

    @Test
    @DisplayName("calcularPrecio devuelve precio sin ejecutar el pipeline completo")
    void testCalcularPrecioSolamente() {
        Order orden = new OrderBuilder(100.0, OrderType.STANDARD, CustomerType.RETAIL)
            .conPremium(true).conCodigo("SAVE10").construir();

        assertEquals(81.0, servicio.calcularPrecio(orden), 0.01);
    }

    @Test
    @DisplayName("STANDARD + premium + BLACKFRIDAY = maximo descuento Q63")
    void testEscenarioMaximoDescuento() {
        Order orden = new OrderBuilder(100.0, OrderType.STANDARD, CustomerType.RETAIL)
            .conPremium(true).conCodigo("BLACKFRIDAY").construir();

        assertEquals(63.0, servicio.calcularPrecio(orden), 0.01);
    }
}
