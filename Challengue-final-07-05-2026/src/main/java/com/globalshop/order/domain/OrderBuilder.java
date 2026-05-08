package com.globalshop.order.domain;

public final class OrderBuilder {

    private final double montoBase;
    private final OrderType tipoOrden;
    private final CustomerType tipoCliente;

    private boolean premium = false;
    private boolean findeSemana = false;
    private String codigoDescuento = null;

    public OrderBuilder(double montoBase, OrderType tipoOrden, CustomerType tipoCliente) {
        this.montoBase = montoBase;
        this.tipoOrden = tipoOrden;
        this.tipoCliente = tipoCliente;
    }

    public OrderBuilder conPremium(boolean premium) {
        this.premium = premium;
        return this;
    }

    public OrderBuilder conFindeSemana(boolean findeSemana) {
        this.findeSemana = findeSemana;
        return this;
    }

    public OrderBuilder conCodigo(String codigo) {
        this.codigoDescuento = codigo;
        return this;
    }

    public Order construir() {
        return new Order(
            montoBase,
            tipoOrden,
            tipoCliente,
            premium,
            findeSemana,
            codigoDescuento
        );
    }
}
