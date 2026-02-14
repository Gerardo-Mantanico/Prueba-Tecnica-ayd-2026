package com.mycompany.pruebatecnica2;

import com.mycompany.pruebatecnica2.modelo.Controlador.ControladorSistema;
import com.mycompany.pruebatecnica2.modelo.Curso;
import com.mycompany.pruebatecnica2.modelo.Estudiante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for ControladorSistema using AAA pattern
 * (Arrange-Act-Assert)
 */
public class ControladorSistemaTest {
    
    private ControladorSistema controlador;
    
    @BeforeEach
    public void setUp() {
        controlador = new ControladorSistema();
    }
    
    @Test
    @DisplayName("Test agregar nuevo estudiante al sistema")
    public void testAgregarNuevoEstudiante() {
        // Arrange
        Estudiante estudiante = new Estudiante(1, "Juan", "Pérez");
        estudiante.agregarCurso(new Curso("Matemáticas", "10"));
        
        // Act
        controlador.agregarEstudiante(estudiante);
        
        // Assert
        assertEquals(1, controlador.cantidadEstudiantes());
        assertEquals(estudiante, controlador.obtenerEstudiante(1));
    }
    
    @Test
    @DisplayName("Test reemplazar estudiante con mismo ID")
    public void testReemplazarEstudianteConMismoId() {
        // Arrange
        Estudiante estudiante1 = new Estudiante(1, "Juan", "Pérez");
        estudiante1.agregarCurso(new Curso("Matemáticas", "10"));
        
        Estudiante estudiante2 = new Estudiante(1, "Pedro", "García");
        estudiante2.agregarCurso(new Curso("Historia", "10"));
        
        // Act
        controlador.agregarEstudiante(estudiante1);
        controlador.agregarEstudiante(estudiante2);
        
        // Assert
        assertEquals(1, controlador.cantidadEstudiantes());
        Estudiante estudianteObtenido = controlador.obtenerEstudiante(1);
        assertEquals("Pedro", estudianteObtenido.getNombre());
        assertEquals("García", estudianteObtenido.getApellido());
    }
    
    @Test
    @DisplayName("Test agregar cursos a estudiante con mismo apellido")
    public void testAgregarCursosEstudianteConMismoApellido() {
        // Arrange
        Estudiante estudiante1 = new Estudiante(1, "Juan", "Pérez");
        estudiante1.agregarCurso(new Curso("Matemáticas", "10"));
        
        Estudiante estudiante2 = new Estudiante(2, "María", "Pérez");
        estudiante2.agregarCurso(new Curso("Historia", "10"));
        
        // Act
        controlador.agregarEstudiante(estudiante1);
        controlador.agregarEstudiante(estudiante2);
        
        // Assert
        assertEquals(1, controlador.cantidadEstudiantes());
        Estudiante estudianteObtenido = controlador.obtenerEstudiante(1);
        assertEquals(2, estudianteObtenido.getCursos().size());
        assertEquals("Matemáticas", estudianteObtenido.getCursos().get(0).getNombre());
        assertEquals("Historia", estudianteObtenido.getCursos().get(1).getNombre());
    }
    
    @Test
    @DisplayName("Test agregar estudiante nulo lanza excepción")
    public void testAgregarEstudianteNulo() {
        // Arrange
        Estudiante estudiante = null;
        
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.agregarEstudiante(estudiante);
        });
    }
    
    @Test
    @DisplayName("Test imprimir sistema vacío")
    public void testImprimirSistemaVacio() {
        // Arrange
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        
        // Act
        controlador.imprimirEstudiantes();
        
        // Assert
        System.setOut(originalOut);
        String output = outputStream.toString();
        assertTrue(output.contains("El sistema está vacío"));
    }
    
    @Test
    @DisplayName("Test imprimir múltiples estudiantes")
    public void testImprimirMultiplesEstudiantes() {
        // Arrange
        Estudiante estudiante1 = new Estudiante(1, "Juan", "Pérez");
        estudiante1.agregarCurso(new Curso("Matemáticas", "10"));
        
        Estudiante estudiante2 = new Estudiante(2, "María", "García");
        estudiante2.agregarCurso(new Curso("Historia", "10"));
        
        controlador.agregarEstudiante(estudiante1);
        controlador.agregarEstudiante(estudiante2);
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        
        // Act
        controlador.imprimirEstudiantes();
        
        // Assert
        System.setOut(originalOut);
        String output = outputStream.toString();
        assertTrue(output.contains("Lista de estudiantes"));
        assertTrue(output.contains("Juan"));
        assertTrue(output.contains("María"));
    }
    
    @Test
    @DisplayName("Test obtener estudiante inexistente")
    public void testObtenerEstudianteInexistente() {
        // Arrange
        int idInexistente = 999;
        
        // Act
        Estudiante estudiante = controlador.obtenerEstudiante(idInexistente);
        
        // Assert
        assertNull(estudiante);
    }
    
    @Test
    @DisplayName("Test limpiar base de datos")
    public void testLimpiarBaseDatos() {
        // Arrange
        Estudiante estudiante1 = new Estudiante(1, "Juan", "Pérez");
        Estudiante estudiante2 = new Estudiante(2, "María", "García");
        controlador.agregarEstudiante(estudiante1);
        controlador.agregarEstudiante(estudiante2);
        
        // Act
        controlador.limpiarBaseDatos();
        
        // Assert
        assertEquals(0, controlador.cantidadEstudiantes());
        assertTrue(controlador.getBaseDatos().isEmpty());
    }
    
    @Test
    @DisplayName("Test estudiante sin cursos")
    public void testEstudianteSinCursos() {
        // Arrange
        Estudiante estudiante = new Estudiante(1, "Juan", "Pérez");
        
        // Act
        controlador.agregarEstudiante(estudiante);
        
        // Assert
        assertEquals(1, controlador.cantidadEstudiantes());
        Estudiante estudianteObtenido = controlador.obtenerEstudiante(1);
        assertNotNull(estudianteObtenido);
        assertTrue(estudianteObtenido.getCursos().isEmpty());
    }
    
    @Test
    @DisplayName("Test comparación de apellidos case-insensitive")
    public void testComparacionApellidosCaseInsensitive() {
        // Arrange
        Estudiante estudiante1 = new Estudiante(1, "Juan", "Pérez");
        estudiante1.agregarCurso(new Curso("Matemáticas", "10"));
        
        Estudiante estudiante2 = new Estudiante(2, "María", "PÉREZ");
        estudiante2.agregarCurso(new Curso("Historia", "10"));
        
        // Act
        controlador.agregarEstudiante(estudiante1);
        controlador.agregarEstudiante(estudiante2);
        
        // Assert
        assertEquals(1, controlador.cantidadEstudiantes());
        Estudiante estudianteObtenido = controlador.obtenerEstudiante(1);
        assertEquals(2, estudianteObtenido.getCursos().size());
    }
}
