/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.systemengineering.tp1paradigmaylenguajesdeprogramacionii.models;

import java.io.Serializable;

/**
 *
 * @author José Fernando Usui y Luciana Rojas
 */
public class Materia implements Serializable {
 
    private final String nombre;
    private final int anio;
    private final int cuatrimestre;
    private final String curso;
    private final Profesor profesor;
    private SituacionMateriaEnum situacion;

    public Materia(String nombre, int anio, int cuatrimestre, String curso, Profesor profesor, SituacionMateriaEnum situacion) {
        this.nombre = nombre;
        this.anio = anio;
        this.cuatrimestre = cuatrimestre;
        this.curso = curso;
        this.profesor = profesor;
        this.situacion = situacion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getAnio() {
        return anio;
    }

    public int getCuatrimestre() {
        return cuatrimestre;
    }

    public String getCurso() {
        return curso;
    }

    public Profesor getProfesor() {
        return profesor;
    }
    
    public SituacionMateriaEnum getSituacion() {
        return situacion;
    }

    public void setSituacion(SituacionMateriaEnum situacion) {
        this.situacion = situacion;
    }

    @Override
    public String toString() {
        return "Materia [\nnombre=" + nombre + ", \nanio=" + anio + ", \ncuatrimestre=" + cuatrimestre + ", \ncurso=" + curso
                + ", \nprofesor=" + profesor + ", \nsituacion=" + situacion + "\n]";
    }
    
}
