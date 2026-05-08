package com.globalshop.order.notification.impl;

import com.globalshop.order.domain.Order;
import com.globalshop.order.notification.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailNotificationService implements NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(EmailNotificationService.class);

    @Override
    public boolean notificarProcesada(Order orden, String email) {
        logger.info(
            "Enviando confirmación a {} | Monto final: Q{} | Código: {}",
            email,
            String.format("%.2f", orden.getPrecioFinal()),
            orden.getCodigoDescuento() != null ? orden.getCodigoDescuento() : "N/A"
        );
        return true;
    }

    @Override
    public boolean notificarError(Order orden, String email, String mensaje) {
        logger.warn(
            "Enviando notificación de error a {} | Error: {}",
            email,
            mensaje
        );
        return true;
    }
}
