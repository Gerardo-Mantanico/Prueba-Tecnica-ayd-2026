package com.globalshop.order.command.impl;

import com.globalshop.order.command.OrderCommand;
import com.globalshop.order.command.OrderCommandResult;
import com.globalshop.order.domain.Order;
import com.globalshop.order.pricing.PriceCalculationFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalculatePriceCommand implements OrderCommand {

    private static final Logger logger = LoggerFactory.getLogger(CalculatePriceCommand.class);

    private final Order orden;
    private final PriceCalculationFacade calculador;
    private double precioOriginal;

    public CalculatePriceCommand(Order orden, PriceCalculationFacade calculador) {
        this.orden = orden;
        this.calculador = calculador;
        this.precioOriginal = orden.getMontoBase();
    }

    @Override
    public OrderCommandResult ejecutar() {
        try {
            double precioFinal = calculador.calcularPrecioFinal(orden);
            orden.setPrecioFinal(precioFinal);

            String mensaje = String.format(
                "Precio calculado: Q%.2f (base) -> Q%.2f (final) | Ahorro: Q%.2f (%.1f%%)",
                orden.getMontoBase(),
                precioFinal,
                orden.calcularAhorro(),
                orden.getPorcentajeDescuento()
            );

            return OrderCommandResult.exito(mensaje);

        } catch (Exception e) {
            return OrderCommandResult.fallo(
                "Error en cálculo de precio para: " + orden,
                e
            );
        }
    }

    @Override
    public void deshacer() {
        orden.setPrecioFinal(precioOriginal);
        logger.info("Se deshizo el cálculo de precio para: {}", orden);
    }

    @Override
    public String getNombre() {
        return "CalculatePriceCommand";
    }

    @Override
    public Order getOrden() {
        return orden;
    }
}
