package org.example;

import code.challenge.cleanArray.Rotacion;


public class Main {
    public static void main(String[] args) {
       
        Rotacion rotacion = new Rotacion();
        // Ejemplo de uso
        Object[] result = Rotacion.cleanArray(new Object[]{1, 2, new Object[]{"3", "4"}, 5});
        
        System.out.println("Hello world!");
    }
}