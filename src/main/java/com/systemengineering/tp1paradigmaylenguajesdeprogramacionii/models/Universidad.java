/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.systemengineering.tp1paradigmaylenguajesdeprogramacionii.models;

import java.util.List;

/**
 *
 * @author José Fernando Usui y Luciana Rojas
 */
public class Universidad {
    
    private List<AsistenciaAlumno> asistencias;

    public Universidad() {
        
    }

    public List<AsistenciaAlumno> getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(List<AsistenciaAlumno> asistencias) {
        this.asistencias = asistencias;
    }
    
}
