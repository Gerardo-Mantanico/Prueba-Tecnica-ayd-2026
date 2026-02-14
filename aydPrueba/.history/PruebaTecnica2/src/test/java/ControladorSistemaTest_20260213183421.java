package com.mycompany.pruebatecnica2;

import com.mycompany.pruebatecnica2.modelo.Controlador.ControladorSistema;
import com.mycompany.pruebatecnica2.modelo.Curso;
import com.mycompany.pruebatecnica2.modelo.Estudiante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias AAA (Arrange-Act-Assert) para ControladorSistema
 */
public class ControladorSistemaTest {
    
    private ControladorSistema controlador;
    
    @BeforeEach
    public void setUp() {
        controlador = new ControladorSistema();
    }
    
    @Test
    @DisplayName("AAA - Agregar un nuevo estudiante al sistema")
    public void testAgregarNuevoEstudiante() {
        // Arrange (Preparar)
        Estudiante estudiante = new Estudiante(1, "Juan", "Pérez");
        Curso curso1 = new Curso("Matemáticas", "A");
        estudiante.agregarCurso(curso1);
        
        // Act (Actuar)
        controlador.agregarEstudiante(estudiante);
        
        // Assert (Afirmar)
        assertEquals(1, controlador.cantidadEstudiantes(), 
                     "Debería haber exactamente 1 estudiante");
        assertNotNull(controlador.obtenerEstudiante(1), 
                      "El estudiante debería existir en el sistema");
        assertEquals("Juan", controlador.obtenerEstudiante(1).getNombre(), 
                     "El nombre del estudiante debería ser Juan");
    }
    
    @Test
    @DisplayName("AAA - Reemplazar estudiante existente con mismo ID")
    public void testReemplazarEstudianteConMismoID() {
        // Arrange (Preparar)
        Estudiante estudianteOriginal = new Estudiante(1, "Juan", "Pérez");
        Curso curso1 = new Curso("Matemáticas", "A");
        estudianteOriginal.agregarCurso(curso1);
        
        Estudiante estudianteNuevo = new Estudiante(1, "Juan Carlos", "Pérez");
        Curso curso2 = new Curso("Física", "B");
        estudianteNuevo.agregarCurso(curso2);
        
        // Act (Actuar)
        controlador.agregarEstudiante(estudianteOriginal);
        controlador.agregarEstudiante(estudianteNuevo);
        
        // Assert (Afirmar)
        assertEquals(1, controlador.cantidadEstudiantes(), 
                     "Debería haber solo 1 estudiante después del reemplazo");
        assertEquals("Juan Carlos", controlador.obtenerEstudiante(1).getNombre(), 
                     "El nombre debería ser el del estudiante nuevo");
        assertEquals(1, controlador.obtenerEstudiante(1).getCursos().size(), 
                     "El estudiante debería tener solo los cursos del nuevo");
    }
    
    @Test
    @DisplayName("AAA - Agregar cursos a estudiante con mismo apellido")
    public void testAgregarCursosEstudianteMismoApellido() {
        // Arrange (Preparar)
        Estudiante estudiante1 = new Estudiante(1, "Juan", "García");
        Curso curso1 = new Curso("Matemáticas", "A");
        estudiante1.agregarCurso(curso1);
        
        Estudiante estudiante2 = new Estudiante(2, "María", "García");
        Curso curso2 = new Curso("Física", "B");
        Curso curso3 = new Curso("Química", "A");
        estudiante2.agregarCurso(curso2);
        estudiante2.agregarCurso(curso3);
        
        // Act (Actuar)
        controlador.agregarEstudiante(estudiante1);
        controlador.agregarEstudiante(estudiante2);
        
        // Assert (Afirmar)
        assertEquals(1, controlador.cantidadEstudiantes(), 
                     "Debería haber solo 1 estudiante");
        assertEquals(3, controlador.obtenerEstudiante(1).getCursos().size(), 
                     "El estudiante debería tener 3 cursos en total");
    }
    
    @Test
    @DisplayName("AAA - Agregar estudiante nulo lanza excepción")
    public void testAgregarEstudianteNulo() {
        // Arrange (Preparar)
        Estudiante estudianteNulo = null;
        
        // Act & Assert (Actuar y Afirmar)
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.agregarEstudiante(estudianteNulo);
        }, "Debería lanzar IllegalArgumentException al agregar estudiante nulo");
    }
    
    @Test
    @DisplayName("AAA - Imprimir estudiantes en sistema vacío")
    public void testImprimirSistemaVacio() {
        // Arrange (Preparar)
        // Sistema ya está vacío por el @BeforeEach
        
        // Act (Actuar)
        controlador.imprimirEstudiantes();
        
        // Assert (Afirmar)
        assertEquals(0, controlador.cantidadEstudiantes(), 
                     "El sistema debería estar vacío");
    }
    
    @Test
    @DisplayName("AAA - Imprimir múltiples estudiantes con cursos")
    public void testImprimirMultiplesEstudiantes() {
        // Arrange (Preparar)
        Estudiante estudiante1 = new Estudiante(1, "Ana", "López");
        estudiante1.agregarCurso(new Curso("Historia", "A"));
        estudiante1.agregarCurso(new Curso("Arte", "B"));
        
        Estudiante estudiante2 = new Estudiante(2, "Carlos", "Martínez");
        estudiante2.agregarCurso(new Curso("Biología", "A"));
        
        // Act (Actuar)
        controlador.agregarEstudiante(estudiante1);
        controlador.agregarEstudiante(estudiante2);
        
        // Assert (Afirmar)
        assertEquals(2, controlador.cantidadEstudiantes(), 
                     "Debería haber 2 estudiantes");
        assertEquals(2, controlador.obtenerEstudiante(1).getCursos().size(), 
                     "El primer estudiante debería tener 2 cursos");
        assertEquals(1, controlador.obtenerEstudiante(2).getCursos().size(), 
                     "El segundo estudiante debería tener 1 curso");
    }
    
    @Test
    @DisplayName("AAA - Obtener estudiante inexistente retorna null")
    public void testObtenerEstudianteInexistente() {
        // Arrange (Preparar)
        int idInexistente = 999;
        
        // Act (Actuar)
        Estudiante resultado = controlador.obtenerEstudiante(idInexistente);
        
        // Assert (Afirmar)
        assertNull(resultado, 
                   "Debería retornar null para un ID que no existe");
    }
    
    @Test
    @DisplayName("AAA - Limpiar base de datos")
    public void testLimpiarBaseDatos() {
        // Arrange (Preparar)
        Estudiante estudiante1 = new Estudiante(1, "Pedro", "Ramírez");
        Estudiante estudiante2 = new Estudiante(2, "Laura", "Torres");
        controlador.agregarEstudiante(estudiante1);
        controlador.agregarEstudiante(estudiante2);
        
        // Act (Actuar)
        controlador.limpiarBaseDatos();
        
        // Assert (Afirmar)
        assertEquals(0, controlador.cantidadEstudiantes(), 
                     "La base de datos debería estar vacía después de limpiar");
    }
    
    @Test
    @DisplayName("AAA - Estudiante sin cursos asignados")
    public void testEstudianteSinCursos() {
        // Arrange (Preparar)
        Estudiante estudiante = new Estudiante(1, "Roberto", "Sánchez");
        
        // Act (Actuar)
        controlador.agregarEstudiante(estudiante);
        
        // Assert (Afirmar)
        assertEquals(0, controlador.obtenerEstudiante(1).getCursos().size(), 
                     "El estudiante no debería tener cursos");
    }
}