/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.systemengineering.tp1paradigmaylenguajesdeprogramacionii.models;

import com.systemengineering.tp1paradigmaylenguajesdeprogramacionii.error.ControlDeInscripcion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author José Fernando Usui y Luciana Rojas
 */
public class Alumno extends Persona implements Serializable {

    private final int nroLegajo;
    private Carrera carrera;
    private final List<Materia> materias = new ArrayList<>();

    public Alumno(int nroLegajo, String nombre, String apellido, String domicilio, Date fechaDeNacimiento) {
        super(nombre, apellido, domicilio, fechaDeNacimiento);
        this.nroLegajo = nroLegajo;
        carrera = null;
    }

    public void matricular(Carrera carrera) {
        if (this.carrera == null) {
            this.carrera = carrera;
        } else {
            throw new ControlDeInscripcion("Ya esta inscripto a una carrera (╯°□°）╯︵ ┻━┻");
        }
    }

    public void inscribirse(Materia materia) {
        materias.add(materia);
    }

    public Carrera verCarreraMatriculado() {
        return carrera;
    }
    
    public List<Materia> verMateriasInscriptas() {
        return materias;
    }

    public int getNroLegajo() {
        return nroLegajo;
    }

    @Override
    public String toString() {
        return "Alumno [\ngetNombre()=" + getNombre() + ", \ngetApellido()=" + getApellido() + ", \n\tverCarreraMatriculado()="
                + verCarreraMatriculado() + ", \n\tverMateriasInscriptas()=" + verMateriasInscriptas() + ", \ngetNroLegajo()="
                + getNroLegajo() + "\n]";
    }

    

}
