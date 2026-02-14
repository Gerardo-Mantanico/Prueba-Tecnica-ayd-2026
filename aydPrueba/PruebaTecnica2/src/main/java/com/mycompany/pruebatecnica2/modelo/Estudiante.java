/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pruebatecnica2.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author gerardo
 */
public class Estudiante {
    private int Id;
    private String nombre;
    private String apellido;
    private List<Curso> cursos;

    public Estudiante(int Id, String nombre, String apellido) {
        this.Id = Id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cursos = new ArrayList<>();
    }
    
    public Estudiante(){
        this.cursos = new ArrayList<>();
    }

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

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public void agregarCurso(Curso curso) {
        if (curso != null) {
            this.cursos.add(curso);
        }
    }

    public void agregarCursos(List<Curso> cursos) {
        if (cursos != null) {
            this.cursos.addAll(cursos);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estudiante that = (Estudiante) o;
        return Id == that.Id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }

    @Override
    public String toString() {
        return "Estudiante{Id=" + Id + ", nombre='" + nombre + "', apellido='" + apellido + "', cursos=" + cursos + "}";
    }
}
