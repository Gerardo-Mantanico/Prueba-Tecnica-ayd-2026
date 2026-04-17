package com.mycompany.exercise;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Exercise2Test {

    @Test
    void convertirI() {
        assertEquals(1, Romano.convertir("I"));
    }

    @Test
    void convertirV() {
        assertEquals(5, Romano.convertir("V"));
    }

    @Test
    void convertirX() {
        assertEquals(10, Romano.convertir("X"));
    }

    @Test
    void convertirL() {
        assertEquals(50, Romano.convertir("L"));
    }

    @Test
    void convertirC() {
        assertEquals(100, Romano.convertir("C"));
    }

    @Test
    void convertirD() {
        assertEquals(500, Romano.convertir("D"));
    }

    @Test
    void convertirM() {
        assertEquals(1000, Romano.convertir("M"));
    }

    @Test
    void convertirMMVI() {
        assertEquals(2006, Romano.convertir("MMVI"));
    }

    @Test
    void convertirMCMXLIV() {
        assertEquals(1944, Romano.convertir("MCMXLIV"));
    }
}
