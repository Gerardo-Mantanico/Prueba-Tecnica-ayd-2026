package com.mycompany.exercise;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Exercise1Test {

    private final Saludo saludo = new Saludo();

    @Test
    void saludoConNombre() {
        assertEquals("Hello, Bob", saludo.saludar("Bob"));
    }

    @Test
    void sinNombre() {
        assertEquals("Hello, my friend", saludo.saludar(null));
    }

    @Test
    void nombreConMayusculas() {
        assertEquals("HELLO JERRY", saludo.saludar("JERRY"));
    }
}
