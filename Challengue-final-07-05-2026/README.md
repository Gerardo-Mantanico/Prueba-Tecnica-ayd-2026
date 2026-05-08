# Procesamiento de Ordenes

Proyecto en Java 17 con Maven. Hice una refactorizacion basica para separar la logica de calculo, construccion de orden, notificacion y procesamiento.

## Que hace

- Calcula el precio final de una orden.
- Aplica descuentos por tipo de orden, tipo de cliente y codigo promocional.
- Guarda la orden, registra logs y simula el envio de correo.

## Como correrlo

```bash
mvn test
mvn test jacoco:report
```

La cobertura queda en `target/site/jacoco/index.html`.

## Estructura

```text
src/main/java/com/globalshop/order/
├── domain/              # Order, OrderBuilder, enums
├── pricing/             # Facade y estrategias de precio/descuento
├── notification/        # servicio de notificacion y adapter
├── command/             # pasos de procesamiento
├── service/             # orquestacion principal
└── config/              # constantes de negocio
```

## Patrones usados

- Builder: para crear la orden sin un constructor largo.
- Strategy: para los distintos calculos de precio y descuentos.
- Facade: para esconder la complejidad del calculo final.
- Adapter: para adaptar el envio de correo.
- Command: para ejecutar el flujo de procesamiento por pasos.

## Code smells que vi

- Metodo muy largo con muchas responsabilidades.
- Muchos `if` anidados.
- Uso de `String` para tipos de orden y cliente.
- Numeros magicos en descuentos y recargos.
- Demasiadas tareas dentro del mismo metodo.

## Tests

Hay tests para:

- Builder de ordenes.
- Estrategias de precio.
- Descuentos promocionales.
- Facade de calculo.
- Integracion del flujo completo.

## Reglas principales

- `STANDARD`: depende del tipo de cliente.
- `EXPRESS`: tiene recargo del 30%.
- `BULK`: aplica descuento por escalones.
- Los codigos promo se aplican al final.

## Nota

Esto no esta pensado como una solucion super formal o de produccion. Es mas bien una version simple para mostrar como separar responsabilidades y que sea mas facil de mantener.
