/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pruebatecnica2.modelo;

/**
 *
 * @author gerardo
 */
public class Estudiante {
    private int Id;
    private String nombre;
    private String apellido;

    public Estudiante(int Id, String nombre, String apellido) {
        this.Id = Id;
        this.nombre = nombre;
        this.apellido = apellido;
    }
    
    public Estudiante(){}

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public int getId() {
        return Id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }
    
    
}
