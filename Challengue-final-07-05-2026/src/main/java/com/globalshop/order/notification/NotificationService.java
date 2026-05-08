package com.globalshop.order.notification;

import com.globalshop.order.domain.Order;

public interface NotificationService {

    boolean notificarProcesada(Order orden, String email);

    boolean notificarError(Order orden, String email, String mensaje);
}
