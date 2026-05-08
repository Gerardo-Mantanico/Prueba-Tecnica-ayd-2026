package com.globalshop.order.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderCommandResult {

    private static final Logger logger = LoggerFactory.getLogger(OrderCommandResult.class);

    private final boolean exitoso;
    private final String mensaje;
    private final Throwable excepcion;

    private OrderCommandResult(boolean exitoso, String mensaje, Throwable excepcion) {
        this.exitoso = exitoso;
        this.mensaje = mensaje;
        this.excepcion = excepcion;
    }

    public static OrderCommandResult exito(String mensaje) {
        logger.info("Comando exitoso: {}", mensaje);
        return new OrderCommandResult(true, mensaje, null);
    }

    public static OrderCommandResult fallo(String mensaje, Throwable ex) {
        logger.error("Comando fallido: {}", mensaje, ex);
        return new OrderCommandResult(false, mensaje, ex);
    }

    public boolean fueExitoso() {
        return exitoso;
    }

    public String getMensaje() {
        return mensaje;
    }

    public Throwable getExcepcion() {
        return excepcion;
    }

    @Override
    public String toString() {
        return String.format("ResultadoComando{exitoso=%s, mensaje='%s'}", exitoso, mensaje);
    }
}
