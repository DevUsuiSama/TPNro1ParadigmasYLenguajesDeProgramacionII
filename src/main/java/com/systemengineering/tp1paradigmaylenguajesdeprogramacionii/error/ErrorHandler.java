/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.systemengineering.tp1paradigmaylenguajesdeprogramacionii.error;

import javax.swing.JOptionPane;

/**
 *
 * @author José Fernando Usui y Luciana Rojas
 */
public class ErrorHandler {
    
    public ErrorHandler() {
        
    }
    
    public void errorControlDeInscripciones(ControlDeInscripcion ex) {
        JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "TP 1 - Paradigma y Lenguajes de Programacion II",
                    JOptionPane.ERROR_MESSAGE);
    }

    public void errorControlDeMatriculacion(ControlDeMatriculacion ex) {
        JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "TP 1 - Paradigma y Lenguajes de Programacion II",
                    JOptionPane.ERROR_MESSAGE);
    }
    
}
