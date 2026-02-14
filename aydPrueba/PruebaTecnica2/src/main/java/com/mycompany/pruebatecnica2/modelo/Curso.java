/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pruebatecnica2.modelo;

/**
 *
 * @author gerardo
 */
public class Curso {
    private String nombre;
    private String grado;

    public Curso(String nombre, String grado){
        this.nombre = nombre;
        this.grado = grado;
        
    }
    public Curso() {
    }

   
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    @Override
    public String toString() {
        return "Curso{nombre='" + nombre + "', grado='" + grado + "'}";
    }
}
