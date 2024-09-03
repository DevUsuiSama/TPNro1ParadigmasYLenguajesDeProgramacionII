/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.systemengineering.tp1paradigmaylenguajesdeprogramacionii.error;

/**
 *
 * @author José Fernando Usui y Luciana Rojas
 */
public class ControlDeMatriculacion extends RuntimeException {

    public ControlDeMatriculacion(String message) {
        super("Control de Matriculación: " + message);
    }

}
