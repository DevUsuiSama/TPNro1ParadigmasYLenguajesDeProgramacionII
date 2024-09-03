/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.systemengineering.tp1paradigmaylenguajesdeprogramacionii.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.systemengineering.tp1paradigmaylenguajesdeprogramacionii.error.ControlDeInscripcion;
import com.systemengineering.tp1paradigmaylenguajesdeprogramacionii.error.ErrorHandler;
import com.systemengineering.tp1paradigmaylenguajesdeprogramacionii.models.Alumno;
import com.systemengineering.tp1paradigmaylenguajesdeprogramacionii.models.Materia;

/**
 *
 * @author José Fernando Usui y Luciana Rojas
 */
public class InscribirseController {

    private final Component component;
    private final List<Alumno> alumnos = new ArrayList<Alumno>();

    public InscribirseController(List<Alumno> alumnos, Component component) {
        this.alumnos.addAll(alumnos.stream().filter(data -> data.verCarreraMatriculado() != null).collect(Collectors.toList()));
        this.component = component;
    }

    public Alumno guardar() {
        Object[] opciones = {"Inscribir", "Cancelar"};

        boolean controlCarreraMatriculada = alumnos.stream().filter(data -> data.verCarreraMatriculado() != null).collect(Collectors.toList()).isEmpty();

        if (controlCarreraMatriculada) {
            JOptionPane.showMessageDialog(component, "No hay alumnos matriculados a las carreras", "Inscribir Alumno", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        JComboBox<String> comboMaterias = new JComboBox<String>(new String[]{"-- Recuerde, hay que seleccionar un Alumno para mostrar la Materia a inscribir --"});

        comboMaterias.setFont(new java.awt.Font("Segoe UI", 1, 14));
        comboMaterias.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        JComboBox<String> comboAlumnos = new JComboBox<String>(alumnos.stream().map((data) -> {
            return data.getNroLegajo() + " - " + data.getNombre() + " " + data.getApellido();
        }).toArray(String[]::new));
        comboAlumnos.setFont(new java.awt.Font("Segoe UI", 1, 14));
        comboAlumnos.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        comboAlumnos.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboAlumnos.getSelectedIndex() != -1) {
                    Alumno alumno = alumnos.get(comboAlumnos.getSelectedIndex());
                    comboMaterias.removeAllItems();
                    comboMaterias.setModel(
                            new DefaultComboBoxModel<>(
                                    alumno.verCarreraMatriculado().getMaterias().stream().filter(data -> {
                                        for (Materia materia : alumno.verMateriasInscriptas())
                                            if (materia == data) return false;
                                        return true;
                                    }).map((data) -> {
                                        return "Nombre: " + data.getNombre() + " | Anio: " + data.getAnio() + " | Profesor: " + data.getProfesor().getNombre() + " " + data.getProfesor().getApellido();
                                    }).toArray(String[]::new)
                            )
                    );
                }
            }

        });

        JLabel labelMaterias = new JLabel("Seleccionar una Materia:");
        labelMaterias.setFont(new java.awt.Font("Segoe UI", 1, 14));
        labelMaterias.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        JLabel labelAlumnos = new JLabel("Seleccionar un Alumno:");
        labelAlumnos.setFont(new java.awt.Font("Segoe UI", 1, 14));
        labelAlumnos.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(labelAlumnos);
        panel.add(comboAlumnos);
        panel.add(Box.createVerticalStrut(5));
        panel.add(labelMaterias);
        panel.add(comboMaterias);

        int resultado = JOptionPane.showOptionDialog(
                component,
                panel,
                "Inscribir Alumno a la Materia",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                opciones,
                opciones[0]);

        if (resultado == JOptionPane.YES_NO_OPTION) {
            if (comboMaterias.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(component, "Ya no hay materias para inscribirse (╯°□°）╯︵ ┻━┻.", "Inscribir Alumno", JOptionPane.WARNING_MESSAGE);
                return null;
            }
            if (comboMaterias.getSelectedItem().equals("-- Recuerde, hay que seleccionar una carrera para mostrar la materia --")) {
                JOptionPane.showMessageDialog(component, "Debe seleccionar un Alumno y luego seleccionar una Materia (╯°□°）╯︵ ┻━┻.", "Inscribir Alumno", JOptionPane.ERROR_MESSAGE);
                return null;
            }

            Alumno alumno = alumnos.get(comboAlumnos.getSelectedIndex());
            Materia materia = alumnos.get(comboAlumnos.getSelectedIndex()).verCarreraMatriculado().getMaterias().stream().filter(data -> {
                String materiaFormateado = "Nombre: " + data.getNombre() + " | Anio: " + data.getAnio() + " | Profesor: " + data.getProfesor().getNombre() + " " + data.getProfesor().getApellido();
                return materiaFormateado.equals(comboMaterias.getSelectedItem());
            }).findFirst().orElse(null);

            try {
                alumno.inscribirse(materia);

                System.out.println(alumno);

                JOptionPane.showMessageDialog(component, "Alumno inscrito exitosamente. 👈(ﾟヮﾟ👈)", "Inscribir Alumno", JOptionPane.INFORMATION_MESSAGE);
                return alumno;
            } catch (ControlDeInscripcion ex) {
                new ErrorHandler().errorControlDeInscripciones(ex);
            }
        } else {
            JOptionPane.showMessageDialog(component, "Operación cancelada.", "Inscribir Alumno", JOptionPane.INFORMATION_MESSAGE);
        }
        return null;
    }

}
