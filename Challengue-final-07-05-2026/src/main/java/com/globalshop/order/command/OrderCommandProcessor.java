package com.globalshop.order.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;

public class OrderCommandProcessor {

    private static final Logger logger = LoggerFactory.getLogger(OrderCommandProcessor.class);
    private static final int MAX_INTENTOS = 3;

    private final Queue<OrderCommand> colaComandos;
    private final List<OrderCommand> comandosEjecutados;
    private final List<OrderCommandResult> resultados;

    public OrderCommandProcessor() {
        this.colaComandos = new LinkedList<>();
        this.comandosEjecutados = new LinkedList<>();
        this.resultados = new LinkedList<>();
    }

    public void agregar(OrderCommand comando) {
        colaComandos.offer(comando);
        logger.debug("Comando encolado: {}", comando.getNombre());
    }

    public void agregarVarios(OrderCommand... comandos) {
        for (OrderCommand cmd : comandos) {
            agregar(cmd);
        }
    }

    public boolean ejecutarTodos() {
        logger.info("Iniciando ejecución de {} comando(s)", colaComandos.size());

        while (!colaComandos.isEmpty()) {
            OrderCommand comando = colaComandos.poll();

            if (!ejecutarConReintentos(comando)) {
                logger.error("Comando fallido (máx intentos alcanzados): {}", comando.getNombre());
                revertir();
                return false;
            }

            comandosEjecutados.add(comando);
        }

        logger.info("Todos los comandos ejecutados exitosamente");
        return true;
    }

    private boolean ejecutarConReintentos(OrderCommand comando) {
        for (int intento = 1; intento <= MAX_INTENTOS; intento++) {
            logger.debug("Ejecutando: {} (intento {}/{})", comando.getNombre(), intento, MAX_INTENTOS);

            try {
                OrderCommandResult resultado = comando.ejecutar();
                resultados.add(resultado);

                if (resultado.fueExitoso()) {
                    logger.info("{}: {}", comando.getNombre(), resultado.getMensaje());
                    return true;
                }

                logger.warn("Intento {} fallido: {}", intento, resultado.getMensaje());

            } catch (Exception e) {
                logger.error("Excepción en intento {}: {}", intento, e.getMessage(), e);
            }

            if (intento < MAX_INTENTOS) {
                try {
                    Thread.sleep((long) Math.pow(2, intento - 1) * 100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return false;
                }
            }
        }

        return false;
    }

    public void revertir() {
        logger.warn("Iniciando ROLLBACK de {} comando(s)", comandosEjecutados.size());

        for (int i = comandosEjecutados.size() - 1; i >= 0; i--) {
            OrderCommand comando = comandosEjecutados.get(i);
            try {
                comando.deshacer();
                logger.info("Deshecho: {}", comando.getNombre());
            } catch (UnsupportedOperationException e) {
                logger.debug("Comando no soporta deshacer: {}", comando.getNombre());
            } catch (Exception e) {
                logger.error("Error deshaciendo comando: {}", comando.getNombre(), e);
            }
        }

        comandosEjecutados.clear();
    }

    public ReporteProcesamiento getReporte() {
        return new ReporteProcesamiento(
            new ArrayList<>(resultados),
            comandosEjecutados.size(),
            resultados.stream().filter(OrderCommandResult::fueExitoso).count()
        );
    }

    public static class ReporteProcesamiento {

        private final List<OrderCommandResult> resultados;
        private final int totalComandos;
        private final long cantExitosos;

        public ReporteProcesamiento(List<OrderCommandResult> resultados,
                                    int totalComandos, long cantExitosos) {
            this.resultados = resultados;
            this.totalComandos = totalComandos;
            this.cantExitosos = cantExitosos;
        }

        public boolean fueExitoso() {
            return cantExitosos == totalComandos;
        }

        public String getResumen() {
            return String.format(
                "Procesamiento: %d/%d comandos exitosos (%.1f%% éxito)",
                cantExitosos,
                totalComandos,
                totalComandos > 0 ? (cantExitosos * 100.0 / totalComandos) : 0
            );
        }

        public List<OrderCommandResult> getResultados() {
            return Collections.unmodifiableList(resultados);
        }

        public List<OrderCommandResult> getFallidos() {
            return resultados.stream()
                .filter(r -> !r.fueExitoso())
                .toList();
        }

        @Override
        public String toString() {
            return getResumen();
        }
    }
}
