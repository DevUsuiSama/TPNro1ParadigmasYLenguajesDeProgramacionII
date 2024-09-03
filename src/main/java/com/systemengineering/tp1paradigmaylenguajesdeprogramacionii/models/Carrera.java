/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.systemengineering.tp1paradigmaylenguajesdeprogramacionii.models;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author José Fernando Usui y Luciana Rojas
 */
public class Carrera implements Serializable {
    
    private final String nombre;
    private final int duracion;
    private final Coordinador coordinador;
    private final double precioInscripcion;
    private final double precioCuota;
    private final List<Materia> materias;

    /**
     * Constructor
     * 
     * @param nombre
     * @param duracion
     * @param coordinador
     * @param precioInscripcion
     * @param precioCuota
     */
    public Carrera(String nombre, int duracion, Coordinador coordinador, double precioInscripcion, double precioCuota, List<Materia> materias) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.coordinador = coordinador;
        this.precioInscripcion = precioInscripcion;
        this.precioCuota = precioCuota;
        this.materias = materias;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public Coordinador getCoordinador() {
        return coordinador;
    }

    public double getPrecioInscripcion() {
        return precioInscripcion;
    }

    public double getPrecioCuota() {
        return precioCuota;
    }

    public List<Materia> getMaterias() {
        return materias;
    }

    @Override
    public String toString() {
        return "Carrera [\nnombre=" + nombre + ", \nduracion=" + duracion + ", \ncoordinador=" + coordinador
                + ", \nprecioInscripcion=" + precioInscripcion + ", \nprecioCuota=" + precioCuota + ", \n\tmaterias=" + materias
                + "\n]";
    }
    
}
