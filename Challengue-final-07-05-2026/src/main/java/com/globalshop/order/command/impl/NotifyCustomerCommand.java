package com.globalshop.order.command.impl;

import com.globalshop.order.command.OrderCommand;
import com.globalshop.order.command.OrderCommandResult;
import com.globalshop.order.domain.Order;
import com.globalshop.order.notification.NotificationService;

public class NotifyCustomerCommand implements OrderCommand {

    private final Order orden;
    private final NotificationService servicio;
    private final String email;

    public NotifyCustomerCommand(Order orden, NotificationService servicio, String email) {
        this.orden = orden;
        this.servicio = servicio;
        this.email = email;
    }

    @Override
    public OrderCommandResult ejecutar() {
        try {
            boolean notificado = servicio.notificarProcesada(orden, email);

            if (!notificado) {
                return OrderCommandResult.fallo(
                    "No se pudo notificar al cliente: " + email,
                    new RuntimeException("El servicio de notificación retornó false")
                );
            }

            return OrderCommandResult.exito("Cliente notificado: " + email);

        } catch (Exception e) {
            return OrderCommandResult.fallo("Error notificando cliente: " + email, e);
        }
    }

    @Override
    public String getNombre() {
        return "NotifyCustomerCommand";
    }

    @Override
    public Order getOrden() {
        return orden;
    }
}
