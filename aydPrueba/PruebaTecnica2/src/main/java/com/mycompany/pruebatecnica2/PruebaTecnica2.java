/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.pruebatecnica2;

import com.mycompany.pruebatecnica2.modelo.Controlador.ControladorSistema;
import com.mycompany.pruebatecnica2.modelo.Curso;
import com.mycompany.pruebatecnica2.modelo.Estudiante;

/**
 *
 * @author gerardo
 */
public class PruebaTecnica2 {

    public static void main(String[] args) {
        System.out.println("=== Sistema de Gestión de Estudiantes ===\n");
        
        // Crear el controlador del sistema
        ControladorSistema controlador = new ControladorSistema();
        
        // Crear estudiantes con cursos
        Estudiante estudiante1 = new Estudiante(1, "Juan", "Pérez");
        estudiante1.agregarCurso(new Curso("Matemáticas", "10"));
        estudiante1.agregarCurso(new Curso("Física", "10"));
        
        Estudiante estudiante2 = new Estudiante(2, "María", "García");
        estudiante2.agregarCurso(new Curso("Historia", "10"));
        estudiante2.agregarCurso(new Curso("Geografía", "10"));
        
        Estudiante estudiante3 = new Estudiante(3, "Carlos", "López");
        estudiante3.agregarCurso(new Curso("Química", "11"));
        
        // Agregar estudiantes al sistema
        System.out.println("Agregando estudiantes...\n");
        controlador.agregarEstudiante(estudiante1);
        controlador.agregarEstudiante(estudiante2);
        controlador.agregarEstudiante(estudiante3);
        
        // Imprimir estudiantes
        controlador.imprimirEstudiantes();
        
        // Demostrar reemplazo por mismo ID
        System.out.println("\n--- Reemplazando estudiante con ID 1 ---\n");
        Estudiante estudianteNuevo = new Estudiante(1, "Pedro", "Martínez");
        estudianteNuevo.agregarCurso(new Curso("Inglés", "10"));
        controlador.agregarEstudiante(estudianteNuevo);
        controlador.imprimirEstudiantes();
        
        // Demostrar agregación de cursos por mismo apellido
        System.out.println("\n--- Agregando estudiante con apellido existente ---\n");
        Estudiante estudianteApellidoRepetido = new Estudiante(4, "Ana", "García");
        estudianteApellidoRepetido.agregarCurso(new Curso("Arte", "10"));
        controlador.agregarEstudiante(estudianteApellidoRepetido);
        controlador.imprimirEstudiantes();
        
        System.out.println("\n--- Total de estudiantes: " + controlador.cantidadEstudiantes() + " ---");
    }
}
