package com.mycompany.pruebatecnica2.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Clase que representa un estudiante en el sistema
 * @author gerardo
 */
public class Estudiante {
    private int id;
    private String nombre;
    private String apellido;
    private List<Curso> cursos;

    public Estudiante(int id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cursos = new ArrayList<>();
    }
    
    public Estudiante() {
        this.cursos = new ArrayList<>();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }
    
    public List<Curso> getCursos() {
        return cursos;
    }
    
    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }
    
    public void agregarCurso(Curso curso) {
        this.cursos.add(curso);
    }
    
    public void agregarCursos(List<Curso> cursos) {
        this.cursos.addAll(cursos);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estudiante that = (Estudiante) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", cursos=" + cursos.size() +
                '}';
    }
}