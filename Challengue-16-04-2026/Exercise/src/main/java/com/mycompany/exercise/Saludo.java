/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.exercise;

public class Saludo {

    public String saludar(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            return "Hello, my friend";
        }

        if (nombre.equals(nombre.toUpperCase())) {
            return "HELLO " + nombre;
        }

        return "Hello, " + nombre;
    }
}
