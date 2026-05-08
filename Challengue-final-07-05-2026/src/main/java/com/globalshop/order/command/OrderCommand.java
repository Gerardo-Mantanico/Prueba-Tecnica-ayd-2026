package com.globalshop.order.command;

import com.globalshop.order.domain.Order;

public interface OrderCommand {

    OrderCommandResult ejecutar();

    default void deshacer() {
        throw new UnsupportedOperationException("Este comando no soporta deshacer");
    }

    String getNombre();

    Order getOrden();
}
