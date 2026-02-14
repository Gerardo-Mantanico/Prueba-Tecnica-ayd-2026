package com.mycompany.pruebatecnica2.modelo.Controlador;

import com.mycompany.pruebatecnica2.modelo.Curso;
import com.mycompany.pruebatecnica2.modelo.Estudiante;
import java.util.HashMap;
import java.util.Map;

/**
 * Controlador del sistema que gestiona estudiantes y cursos
 * Usa una HashMap como base de datos donde la clave es el Estudiante
 * @author gerardo
 */
public class ControladorSistema {
    
    private HashMap<Integer, Estudiante> baseDatos;
    
    public ControladorSistema() {
        this.baseDatos = new HashMap<>();
    }
    
    /**
     * Método para imprimir todos los estudiantes y sus cursos
     */
    public void imprimirEstudiantes() {
        System.out.println("========== Lista de Estudiantes ==========");
        
        if (baseDatos.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }
        
        for (Map.Entry<Integer, Estudiante> entry : baseDatos.entrySet()) {
            Estudiante estudiante = entry.getValue();
            System.out.println("\nID: " + estudiante.getId());
            System.out.println("Nombre: " + estudiante.getNombre() + " " + estudiante.getApellido());
            System.out.println("Cursos:");
            
            if (estudiante.getCursos().isEmpty()) {
                System.out.println("  - Sin cursos asignados");
            } else {
                for (Curso curso : estudiante.getCursos()) {
                    System.out.println("  - " + curso.getNombre() + " (Grado: " + curso.getGrado() + ")");
                }
            }
        }
        System.out.println("==========================================");
    }
    
    /**
     * Método para agregar un estudiante al sistema
     * - Si el estudiante tiene el mismo ID, reemplaza la entrada
     * - Si tiene el mismo apellido que otro estudiante, agrega los cursos al existente
     */
    public void agregarEstudiante(Estudiante nuevoEstudiante) {
        if (nuevoEstudiante == null) {
            throw new IllegalArgumentException("El estudiante no puede ser nulo");
        }
        
        // Verificar si ya existe un estudiante con el mismo ID
        if (baseDatos.containsKey(nuevoEstudiante.getId())) {
            // Reemplazar con el nuevo estudiante
            baseDatos.put(nuevoEstudiante.getId(), nuevoEstudiante);
            System.out.println("Estudiante con ID " + nuevoEstudiante.getId() + " actualizado.");
            return;
        }
        
        // Verificar si existe un estudiante con el mismo apellido
        for (Map.Entry<Integer, Estudiante> entry : baseDatos.entrySet()) {
            Estudiante estudianteExistente = entry.getValue();
            
            if (estudianteExistente.getApellido().equalsIgnoreCase(nuevoEstudiante.getApellido()) 
                && estudianteExistente.getId() != nuevoEstudiante.getId()) {
                // Agregar los cursos del nuevo estudiante al existente
                estudianteExistente.agregarCursos(nuevoEstudiante.getCursos());
                System.out.println("Cursos agregados al estudiante existente con apellido: " + 
                                   estudianteExistente.getApellido());
                return;
            }
        }
        
        // Si no existe, agregar el nuevo estudiante
        baseDatos.put(nuevoEstudiante.getId(), nuevoEstudiante);
        System.out.println("Estudiante agregado: " + nuevoEstudiante.getNombre() + " " + 
                           nuevoEstudiante.getApellido());
    }
    
    /**
     * Obtiene un estudiante por su ID
     */
    public Estudiante obtenerEstudiante(int id) {
        return baseDatos.get(id);
    }
    
    /**
     * Obtiene la base de datos completa
     */
    public HashMap<Integer, Estudiante> getBaseDatos() {
        return baseDatos;
    }
    
    /**
     * Limpia toda la base de datos
     */
    public void limpiarBaseDatos() {
        baseDatos.clear();
    }
    
    /**
     * Retorna el número de estudiantes en el sistema
     */
    public int cantidadEstudiantes() {
        return baseDatos.size();
    }
}