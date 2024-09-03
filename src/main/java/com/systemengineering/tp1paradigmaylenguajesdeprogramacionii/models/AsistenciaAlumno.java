/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.systemengineering.tp1paradigmaylenguajesdeprogramacionii.models;

import java.util.Date;

/**
 *
 * @author José Fernando Usui y Luciana Rojas
 */
public class AsistenciaAlumno {
    
    private Alumno alumno;
    private boolean estado;
    private Date fechaActual;
    private Materia materia;

    public AsistenciaAlumno(Alumno alumno, boolean estado, Date fechaActual, Materia materia) {
        this.alumno = alumno;
        this.estado = estado;
        this.fechaActual = fechaActual;
        this.materia = materia;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public boolean isEstado() {
        return estado;
    }

    public Date getFechaActual() {
        return fechaActual;
    }

    public Materia getMateria() {
        return materia;
    }
    
}
