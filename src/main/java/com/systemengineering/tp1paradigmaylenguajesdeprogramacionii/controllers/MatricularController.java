/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.systemengineering.tp1paradigmaylenguajesdeprogramacionii.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.systemengineering.tp1paradigmaylenguajesdeprogramacionii.error.ControlDeMatriculacion;
import com.systemengineering.tp1paradigmaylenguajesdeprogramacionii.error.ErrorHandler;
import com.systemengineering.tp1paradigmaylenguajesdeprogramacionii.models.Alumno;
import com.systemengineering.tp1paradigmaylenguajesdeprogramacionii.models.Carrera;
import com.systemengineering.tp1paradigmaylenguajesdeprogramacionii.util.Formato;

/**
 *
 * @author José Fernando Usui y Luciana Rojas
 */
public class MatricularController {

    private final Component component;
    private final List<Alumno> alumnos = new ArrayList<Alumno>();
    private final List<Carrera> carreras = new ArrayList<Carrera>();

    public MatricularController(List<Alumno> alumnos, List<Carrera> carreras, Component component) {
        this.alumnos.addAll(alumnos.stream().filter(data -> data.verCarreraMatriculado() == null).collect(Collectors.toList()));
        this.carreras.addAll(carreras);
        this.component = component;
    }

    public Alumno guardar() {
        Object[] opciones = {"Matricular", "Cancelar"};

        JComboBox<String> comboCarreras = new JComboBox<String>(carreras.stream().map((data) -> {
            return data.getNombre() + " | Duración: " + data.getDuracion() + " | Valor de Inscripción: $" + Formato.formatearDecimalEnPrecio(data.getPrecioInscripcion()) + " | Valor de la Cuota: " + Formato.formatearDecimalEnPrecio(data.getPrecioCuota());
        }).toArray(String[]::new));
        comboCarreras.setFont(new java.awt.Font("Segoe UI", 1, 14));
        comboCarreras.setAlignmentX(JComboBox.LEFT_ALIGNMENT);
        
        JComboBox<String> comboAlumnos = new JComboBox<String>(alumnos.stream().map((data) -> {
            return data.getNroLegajo() + " - " + data.getNombre() + " " + data.getApellido();
        }).toArray(String[]::new));

        if (comboAlumnos.getItemCount() == 0) {
            JOptionPane.showMessageDialog(component, "Ya no hay alumnos disponible para matricular", "Matricular Alumno", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        comboAlumnos.setFont(new java.awt.Font("Segoe UI", 1, 14));
        comboAlumnos.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        JLabel labelCarreras = new JLabel("Seleccionar una Carrera:");
        labelCarreras.setFont(new java.awt.Font("Segoe UI", 1, 14));
        labelCarreras.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        
        JLabel labelAlumnos = new JLabel("Seleccionar un Alumno:");
        labelAlumnos.setFont(new java.awt.Font("Segoe UI", 1, 14));
        labelAlumnos.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(labelCarreras);
        panel.add(comboCarreras);
        panel.add(Box.createVerticalStrut(5));
        panel.add(labelAlumnos);
        panel.add(comboAlumnos);

        int resultado = JOptionPane.showOptionDialog(
                component,
                panel,
                "Matricular Alumno a la Carrera",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                opciones,
                opciones[0]);

        if (resultado == JOptionPane.YES_NO_OPTION) {
            if (comboAlumnos.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(component, "Ya no hay Alumnos para matricular (╯°□°）╯︵ ┻━┻.", "Matricular Alumno", JOptionPane.WARNING_MESSAGE);
                return null;
            }
            
            Alumno alumno = alumnos.get(comboAlumnos.getSelectedIndex());
            Carrera carrera = carreras.get(comboCarreras.getSelectedIndex());
            
            try {
                alumno.matricular(carrera);
                
                System.out.println(alumno);
                
                JOptionPane.showMessageDialog(component, "Alumno matriculado exitosamente. 👈(ﾟヮﾟ👈)", "Matricular Alumno", JOptionPane.INFORMATION_MESSAGE);
                return alumno;
            } catch (ControlDeMatriculacion ex) {
                new ErrorHandler().errorControlDeMatriculacion(ex);
            }
        } else {
            JOptionPane.showMessageDialog(component, "Operación cancelada.", "Matricular Alumno", JOptionPane.INFORMATION_MESSAGE);
        }
        return null;
    }

}
