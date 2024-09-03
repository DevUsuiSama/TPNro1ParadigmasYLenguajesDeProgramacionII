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
public class Profesor extends Persona {
    
    private final Universidad universidad = new Universidad();

    public Profesor(String nombre, String apellido, String domicilio, Date fechaDeNacimiento) {
        super(nombre, apellido, domicilio, fechaDeNacimiento);
    }

    public Materia asignarSituacionMateria(Materia materia, SituacionMateriaEnum situacionMateriaEnum) {
        materia.setSituacion(situacionMateriaEnum);
        return materia;
    }
    
    public Universidad getUniversidad() {
        return universidad;
    }
    
}
