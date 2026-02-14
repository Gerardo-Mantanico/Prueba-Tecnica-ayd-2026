package com.mycompany.pruebatecnica2;

import com.mycompany.pruebatecnica2.modelo.Controlador.ControladorSistema;
import com.mycompany.pruebatecnica2.modelo.Curso;
import com.mycompany.pruebatecnica2.modelo.Estudiante;

/**
 * Clase principal de demostración del sistema
 * @author gerardo
 */
public class PruebaTecnica2 {

    public static void main(String[] args) {
        System.out.println("=== Sistema de Gestión de Estudiantes ===\n");
        
        ControladorSistema controlador = new ControladorSistema();
        
        // Crear estudiantes y cursos
        Estudiante est1 = new Estudiante(1, "Juan", "Pérez");
        est1.agregarCurso(new Curso("Matemáticas", "A"));
        est1.agregarCurso(new Curso("Física", "B"));
        
        Estudiante est2 = new Estudiante(2, "María", "García");
        est2.agregarCurso(new Curso("Química", "A"));
        
        Estudiante est3 = new Estudiante(3, "Carlos", "Pérez");
        est3.agregarCurso(new Curso("Historia", "B"));
        
        // Agregar estudiantes
        System.out.println("--- Agregando estudiantes ---");
        controlador.agregarEstudiante(est1);
        controlador.agregarEstudiante(est2);
        controlador.agregarEstudiante(est3); // Mismo apellido que est1
        
        System.out.println("\n");
        
        // Imprimir todos los estudiantes
        controlador.imprimirEstudiantes();
    }
}