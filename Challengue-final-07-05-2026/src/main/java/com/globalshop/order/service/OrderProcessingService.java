package com.globalshop.order.service;

import com.globalshop.order.command.*;
import com.globalshop.order.command.impl.*;
import com.globalshop.order.domain.*;
import com.globalshop.order.pricing.PriceCalculationFacade;
import com.globalshop.order.notification.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderProcessingService {

    private static final Logger logger = LoggerFactory.getLogger(OrderProcessingService.class);

    private final PriceCalculationFacade calculador;
    private final NotificationService servicio;

    public OrderProcessingService(PriceCalculationFacade calculador, NotificationService servicio) {
        this.calculador = calculador;
        this.servicio = servicio;
    }

    public OrderCommandProcessor.ReporteProcesamiento procesarOrden(Order orden, String email) {
        logger.info("PROCESANDO ORDEN: {}", orden);

        OrderCommandProcessor procesador = new OrderCommandProcessor();
        procesador.agregar(new CalculatePriceCommand(orden, calculador));
        procesador.agregar(new NotifyCustomerCommand(orden, servicio, email));

        boolean exito = procesador.ejecutarTodos();

        if (!exito) {
            logger.error("PROCESAMIENTO FALLIDO PARA ORDEN: {}", orden);
            servicio.notificarError(orden, email, "Error en pipeline de procesamiento");
        }

        OrderCommandProcessor.ReporteProcesamiento reporte = procesador.getReporte();
        logger.info("RESULTADO: {}", reporte.getResumen());

        return reporte;
    }

    public double calcularPrecio(Order orden) {
        logger.debug("Calculando precio para: {}", orden);
        return calculador.calcularPrecioFinal(orden);
    }
}
