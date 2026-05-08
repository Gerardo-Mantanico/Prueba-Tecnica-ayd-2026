package com.globalshop.order.notification.impl;

import com.globalshop.order.domain.Order;
import com.globalshop.order.notification.LegacyEmailClient;
import com.globalshop.order.notification.NotificationService;

/**
 * Adapter: convierte la interfaz de LegacyEmailClient a NotificationService.
 *
 * LegacyEmailClient tiene métodos sendEmail/sendErrorAlert que son incompatibles
 * con notificarProcesada/notificarError. Este adapter hace el puente.
 */
public class LegacyEmailAdapter implements NotificationService {

    private final LegacyEmailClient legacyClient;

    public LegacyEmailAdapter(LegacyEmailClient legacyClient) {
        this.legacyClient = legacyClient;
    }

    @Override
    public boolean notificarProcesada(Order orden, String email) {
        if (!legacyClient.ping()) return false;

        String subject = "GlobalShop - Confirmacion de pedido";
        String body = String.format(
            "Tu pedido fue procesado. Monto final: Q%.2f | Tipo: %s",
            orden.getPrecioFinal(),
            orden.getTipoOrden()
        );

        legacyClient.sendEmail(email, subject, body);
        return true;
    }

    @Override
    public boolean notificarError(Order orden, String email, String mensaje) {
        if (!legacyClient.ping()) return false;

        legacyClient.sendErrorAlert(email, "ORDER_ERROR", mensaje);
        return true;
    }
}
