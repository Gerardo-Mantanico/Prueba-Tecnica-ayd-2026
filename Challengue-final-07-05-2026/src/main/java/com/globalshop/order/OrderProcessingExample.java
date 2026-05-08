package com.globalshop.order;

import com.globalshop.order.domain.*;
import com.globalshop.order.pricing.PriceCalculationFacade;
import com.globalshop.order.service.OrderProcessingService;
import com.globalshop.order.notification.impl.EmailNotificationService;
import com.globalshop.order.command.OrderCommandProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderProcessingExample {

    private static final Logger logger = LoggerFactory.getLogger(OrderProcessingExample.class);

    public static void main(String[] args) {
        logger.info("EJEMPLO DE PROCESAMIENTO DE ORDENES");

        PriceCalculationFacade calculador = new PriceCalculationFacade();

        OrderProcessingService servicio = new OrderProcessingService(
            calculador,
            new EmailNotificationService()
        );

        logger.info("=== Ejemplo 1: Orden STANDARD con descuento ===");

        Order orden1 = new OrderBuilder(100.0, OrderType.STANDARD, CustomerType.RETAIL)
            .conPremium(true)
            .conCodigo("SAVE10")
            .construir();

        logger.info("Orden creada: {}", orden1);

        OrderCommandProcessor.ReporteProcesamiento reporte =
            servicio.procesarOrden(orden1, "cliente@ejemplo.com");

        logger.info("Resultado: {}", reporte.getResumen());
        logger.info("  Precio base: Q{}", String.format("%.2f", orden1.getMontoBase()));
        logger.info("  Precio final: Q{}", String.format("%.2f", orden1.getPrecioFinal()));
        logger.info("  Ahorro: Q{}", String.format("%.2f", orden1.calcularAhorro()));

        logger.info("=== Ejemplo 2: Orden BULK con descuento escalonado ===");

        Order orden2 = new OrderBuilder(6000.0, OrderType.BULK, CustomerType.WHOLESALE)
            .conCodigo("BLACKFRIDAY")
            .construir();

        reporte = servicio.procesarOrden(orden2, "mayorista@proveedor.com");

        logger.info("Resultado: {}", reporte.getResumen());
        logger.info("  Precio base: Q{}", String.format("%.2f", orden2.getMontoBase()));
        logger.info("  Precio final: Q{}", String.format("%.2f", orden2.getPrecioFinal()));
        logger.info("  Ahorro: Q{}", String.format("%.2f", orden2.calcularAhorro()));

        logger.info("=== Ejemplo 3: Orden EXPRESS internacional fin de semana ===");

        Order orden3 = new OrderBuilder(500.0, OrderType.EXPRESS, CustomerType.INTERNATIONAL)
            .conFindeSemana(true)
            .conPremium(true)
            .construir();

        reporte = servicio.procesarOrden(orden3, "intl@exterior.com");

        logger.info("Resultado: {}", reporte.getResumen());
        logger.info("  Precio base: Q{}", String.format("%.2f", orden3.getMontoBase()));
        logger.info("  Precio final: Q{}", String.format("%.2f", orden3.getPrecioFinal()));

        logger.info("=== Ejemplo 4: Cálculo de precio solamente ===");

        Order orden4 = new OrderBuilder(250.0, OrderType.STANDARD, CustomerType.RETAIL)
            .conPremium(false)
            .conCodigo("WELCOME")
            .construir();

        double precio = servicio.calcularPrecio(orden4);
        logger.info("Cotización: Q{}", String.format("%.2f", precio));

        logger.info("=== Ejemplo 5: Procesamiento en lote ===");

        Order[] ordenes = {
            new OrderBuilder(100.0, OrderType.STANDARD, CustomerType.RETAIL).conPremium(true).construir(),
            new OrderBuilder(1000.0, OrderType.BULK, CustomerType.WHOLESALE).construir(),
            new OrderBuilder(300.0, OrderType.EXPRESS, CustomerType.INTERNATIONAL).conFindeSemana(true).construir(),
            new OrderBuilder(500.0, OrderType.STANDARD, CustomerType.RETAIL).conCodigo("SAVE20").construir(),
            new OrderBuilder(2000.0, OrderType.BULK, CustomerType.INTERNATIONAL).conCodigo("SAVE10").construir(),
        };

        double totalBase = 0;
        double totalFinal = 0;
        int exitosos = 0;

        for (int i = 0; i < ordenes.length; i++) {
            Order orden = ordenes[i];
            totalBase += orden.getMontoBase();

            reporte = servicio.procesarOrden(
                orden,
                String.format("cliente%d@ejemplo.com", i + 1)
            );

            if (reporte.fueExitoso()) {
                totalFinal += orden.getPrecioFinal();
                exitosos++;
                logger.info("  {} OK Q{} -> Q{}",
                    i + 1,
                    String.format("%.2f", orden.getMontoBase()),
                    String.format("%.2f", orden.getPrecioFinal())
                );
            } else {
                logger.warn("  {} FALLO en procesamiento", i + 1);
            }
        }

        logger.info("RESUMEN DE LOTE:");
        logger.info("  Total órdenes: {}", ordenes.length);
        logger.info("  Exitosas: {}", exitosos);
        logger.info("  Total base: Q{}", String.format("%.2f", totalBase));
        logger.info("  Total final: Q{}", String.format("%.2f", totalFinal));
        logger.info("  Ahorros totales: Q{}", String.format("%.2f", totalBase - totalFinal));

        logger.info("=== Ejemplo 6: Registrar descuento personalizado ===");

        PriceCalculationFacade.DiscountRegistry registro =
            new PriceCalculationFacade.DiscountRegistry();

        registro.registrar("FERIADO", new com.globalshop.order.pricing.discount.DiscountStrategy() {
            @Override
            public double aplicarDescuento(double precioActual) {
                return precioActual * 0.85;
            }
            @Override
            public String getCodigo() { return "FERIADO"; }
            @Override
            public double getPorcentaje() { return 15.0; }
        });

        logger.info("Descuento FERIADO registrado: {}",
            registro.esCodigoValido("FERIADO") ? "valido" : "invalido");
    }
}
