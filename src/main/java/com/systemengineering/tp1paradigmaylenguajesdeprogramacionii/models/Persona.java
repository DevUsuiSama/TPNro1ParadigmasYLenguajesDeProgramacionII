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
public abstract class Persona {
    
    private final String nombre, apellido, domicilio;
    private final Date fechaDeNacimiento;
    
    public Persona(String nombre, String apellido, String domicilio, Date fechaDeNacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public Date getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }
    
}
