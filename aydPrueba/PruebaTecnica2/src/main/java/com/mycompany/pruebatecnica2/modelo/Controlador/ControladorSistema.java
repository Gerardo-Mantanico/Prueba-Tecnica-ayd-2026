/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pruebatecnica2.modelo.Controlador;

import com.mycompany.pruebatecnica2.modelo.Estudiante;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author gerardo
 */
public class ControladorSistema {
    
    private HashMap<Integer, Estudiante> baseDatos;

    public ControladorSistema() {
        this.baseDatos = new HashMap<>();
    }

    public HashMap<Integer, Estudiante> getBaseDatos() {
        return baseDatos;
    }
    
    //metodo para imprimir estudiantes
    public void imprimirEstudiantes(){
        if (baseDatos.isEmpty()) {
            System.out.println("El sistema está vacío");
            return;
        }
        
        System.out.println("Lista de estudiantes:");
        for (Map.Entry<Integer, Estudiante> entry : baseDatos.entrySet()) {
            Estudiante estudiante = entry.getValue();
            System.out.println(estudiante);
        }
    }
    
    public void agregarEstudiante(Estudiante estudiante){
        if (estudiante == null) {
            throw new IllegalArgumentException("El estudiante no puede ser nulo");
        }
        
        // Buscar estudiante con el mismo apellido (case-insensitive)
        Estudiante estudianteConMismoApellido = null;
        for (Estudiante e : baseDatos.values()) {
            if (e.getApellido() != null && estudiante.getApellido() != null &&
                e.getApellido().equalsIgnoreCase(estudiante.getApellido()) &&
                e.getId() != estudiante.getId()) {
                estudianteConMismoApellido = e;
                break;
            }
        }
        
        // Si existe un estudiante con el mismo apellido, agregar los cursos del nuevo al existente
        if (estudianteConMismoApellido != null) {
            estudianteConMismoApellido.agregarCursos(estudiante.getCursos());
        } else {
            // Si el estudiante tiene el mismo ID que uno existente, reemplazar
            // Si no existe, simplemente agregar
            baseDatos.put(estudiante.getId(), estudiante);
        }
    }
    
    public Estudiante obtenerEstudiante(int id) {
        return baseDatos.get(id);
    }
    
    public void limpiarBaseDatos() {
        baseDatos.clear();
    }
    
    public int cantidadEstudiantes() {
        return baseDatos.size();
    }
}
