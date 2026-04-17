package com.mycompany.exercise;

import java.util.Map;

public final class Romano {

    private static final Map<Character, Integer> VALORES = Map.of(
            'I', 1,
            'V', 5,
            'X', 10,
            'L', 50,
            'C', 100,
            'D', 500,
            'M', 1000
    );

    private Romano() {
    }

    public static int convertir(String romano) {
        if (romano == null || romano.isBlank()) {
            throw new IllegalArgumentException("El número romano no puede estar vacío");
        }

        int total = 0;
        int valorAnterior = 0;

        for (int indice = romano.length() - 1; indice >= 0; indice--) {
            char simbolo = Character.toUpperCase(romano.charAt(indice));
            Integer valor = VALORES.get(simbolo);

            if (valor == null) {
                throw new IllegalArgumentException("Símbolo romano inválido: " + simbolo);
            }

            if (valor < valorAnterior) {
                total -= valor;
            } else {
                total += valor;
            }

            valorAnterior = valor;
        }

        return total;
    }
}