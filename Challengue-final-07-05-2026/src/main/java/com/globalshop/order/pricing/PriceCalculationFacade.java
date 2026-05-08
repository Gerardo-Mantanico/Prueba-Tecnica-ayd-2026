package com.globalshop.order.pricing;

import com.globalshop.order.domain.*;
import com.globalshop.order.pricing.discount.*;
import com.globalshop.order.pricing.strategy.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class PriceCalculationFacade {

    private static final Logger logger = LoggerFactory.getLogger(PriceCalculationFacade.class);

    private final Map<OrderType, PricingStrategy> estrategias;
    private final DiscountRegistry registroDescuentos;

    public PriceCalculationFacade() {
        this.estrategias = inicializarEstrategias();
        this.registroDescuentos = new DiscountRegistry();
    }

    private Map<OrderType, PricingStrategy> inicializarEstrategias() {
        Map<OrderType, PricingStrategy> mapa = new HashMap<>();
        mapa.put(OrderType.STANDARD, new StandardOrderStrategy());
        mapa.put(OrderType.EXPRESS, new ExpressOrderStrategy());
        mapa.put(OrderType.BULK, new BulkOrderStrategy());
        return mapa;
    }

    public double calcularPrecioFinal(Order orden) {
        logger.debug("Iniciando cálculo de precio para: {}", orden);

        double precioEstrategia = aplicarEstrategia(orden);
        logger.debug("Precio después de estrategia: Q{}", String.format("%.2f", precioEstrategia));

        double precioFinal = aplicarDescuento(orden, precioEstrategia);
        logger.debug("Precio final: Q{}", String.format("%.2f", precioFinal));

        return precioFinal;
    }

    private double aplicarEstrategia(Order orden) {
        PricingStrategy estrategia = estrategias.get(orden.getTipoOrden());

        if (estrategia == null) {
            throw new IllegalArgumentException(
                "No hay estrategia para tipo de orden: " + orden.getTipoOrden()
            );
        }

        logger.debug("Aplicando estrategia: {}", estrategia.getNombre());
        return estrategia.calcularPrecio(orden);
    }

    private double aplicarDescuento(Order orden, double precioActual) {
        String codigo = orden.getCodigoDescuento();

        if (codigo == null || codigo.trim().isEmpty()) {
            logger.debug("Sin descuento promocional");
            return precioActual;
        }

        DiscountStrategy descuento = registroDescuentos.getDescuento(codigo);
        double precioFinal = descuento.aplicarDescuento(precioActual);

        logger.debug("Descuento '{}' aplicado: {}%",
            descuento.getCodigo(),
            descuento.getPorcentaje()
        );

        return precioFinal;
    }

    public static class DiscountRegistry {

        private static final Logger logger = LoggerFactory.getLogger(DiscountRegistry.class);

        private final Map<String, DiscountStrategy> descuentos;

        public DiscountRegistry() {
            this.descuentos = new HashMap<>();
            registrarDescuentosBase();
        }

        private void registrarDescuentosBase() {
            registrar("SAVE10", new Save10Discount());
            registrar("SAVE20", new Save20Discount());
            registrar("BLACKFRIDAY", new BlackFridayDiscount());
            registrar("WELCOME", new WelcomeDiscount());
        }

        public void registrar(String codigo, DiscountStrategy descuento) {
            descuentos.put(codigo, descuento);
            logger.info("Descuento registrado: {}", codigo);
        }

        public DiscountStrategy getDescuento(String codigo) {
            return descuentos.getOrDefault(codigo, new NoDiscount());
        }

        public boolean esCodigoValido(String codigo) {
            return codigo != null && descuentos.containsKey(codigo);
        }
    }
}
