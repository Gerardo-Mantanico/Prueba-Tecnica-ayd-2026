package com.globalshop.order.notification.impl;

import com.globalshop.order.domain.Order;
import com.globalshop.order.notification.NotificationService;

public class NoOpNotificationService implements NotificationService {

    @Override
    public boolean notificarProcesada(Order orden, String email) {
        return true;
    }

    @Override
    public boolean notificarError(Order orden, String email, String mensaje) {
        return true;
    }
}
