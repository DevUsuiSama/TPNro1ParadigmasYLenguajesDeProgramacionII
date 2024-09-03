/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.systemengineering.tp1paradigmaylenguajesdeprogramacionii.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
import com.systemengineering.tp1paradigmaylenguajesdeprogramacionii.models.Profesor;
import com.systemengineering.tp1paradigmaylenguajesdeprogramacionii.models.SituacionMateriaEnum;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 *
 * @author José Fernando Usui y Luciana Rojas
 */
public class SituacionFinalController {

    private final Component component;
    private final List<Profesor> profesores = new ArrayList<Profesor>();
    private final List<Alumno> alumnos = new ArrayList<Alumno>();

    public SituacionFinalController(Profesor profesores[], List<Alumno> alumnos, Component component) {
        Collections.addAll(this.profesores, profesores);
        this.alumnos.addAll(alumnos.stream().filter(data -> data.verCarreraMatriculado() != null).collect(Collectors.toList()));
        this.component = component;
    }

    public Alumno guardar() {
        Object[] opciones = {"Matricular", "Cancelar"};

        boolean controlCarreraMatriculada = alumnos.stream().filter(alumno -> alumno.verCarreraMatriculado() != null).collect(Collectors.toList()).isEmpty();

        if (controlCarreraMatriculada) {
            JOptionPane.showMessageDialog(component, "No hay alumnos matriculados a las carrera", "Inscribir Alumno", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        JComboBox<String> comboMaterias = new JComboBox<String>(new String[]{"-- Recuerde, hay que seleccionar un Alumno para mostrar la Materia y poder asignar una Situación Final --"});
        comboMaterias.setFont(new java.awt.Font("Segoe UI", 1, 14));
        comboMaterias.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        JComboBox<String> comboSituacion = new JComboBox<String>(Arrays.stream(SituacionMateriaEnum.values()).map(Enum::name).toArray(String[]::new));
        comboSituacion.setFont(new java.awt.Font("Segoe UI", 1, 14));
        comboSituacion.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        
        JComboBox<String> comboAlumnos = new JComboBox<String>(alumnos.stream().filter(alumno -> alumno.verCarreraMatriculado() != null).map((data) -> {
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
                                    alumno.verMateriasInscriptas().stream().map((data) -> {
                                        return "Nombre: " + data.getNombre() + " | Anio: " + data.getAnio() + " | Profesor: " + data.getProfesor().getNombre() + " " + data.getProfesor().getApellido();
                                    }).toArray(String[]::new)
                            )
                    );
                }
            }

        });

        JLabel labelAlumnos = new JLabel("Seleccionar un Alumno:");
        labelAlumnos.setFont(new java.awt.Font("Segoe UI", 1, 14));
        labelAlumnos.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        
        JLabel labelSituacion = new JLabel("Seleccionar una Situacion:");
        labelSituacion.setFont(new java.awt.Font("Segoe UI", 1, 14));
        labelSituacion.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        
        JLabel labelMaterias = new JLabel("Seleccionar una Materia:");
        labelMaterias.setFont(new java.awt.Font("Segoe UI", 1, 14));
        labelMaterias.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(labelAlumnos);
        panel.add(comboAlumnos);
        panel.add(Box.createVerticalStrut(5));
        panel.add(labelMaterias);
        panel.add(comboMaterias);
        panel.add(Box.createVerticalStrut(5));
        panel.add(labelSituacion);
        panel.add(comboSituacion);

        int resultado = JOptionPane.showOptionDialog(
                component,
                panel,
                "Situacion de Materia Final",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                opciones,
                opciones[0]);

        if (resultado == JOptionPane.YES_NO_OPTION) {
            if (comboMaterias.getSelectedItem().equals("-- Recuerde, hay que seleccionar una carrera para mostrar la materia --")) {
                JOptionPane.showMessageDialog(component, "Debe seleccionar un Alumno y luego seleccionar una Materia (╯°□°）╯︵ ┻━┻.", "Inscribir Alumno", JOptionPane.ERROR_MESSAGE);
                return null;
            }

            Alumno alumno = alumnos.get(comboAlumnos.getSelectedIndex());
            SituacionMateriaEnum situacion = SituacionMateriaEnum.valueOf((String) comboSituacion.getSelectedItem());
            Materia materia = alumnos.get(comboAlumnos.getSelectedIndex()).verMateriasInscriptas().get(comboMaterias.getSelectedIndex());
            Profesor profesor = materia.getProfesor();
            
            try {
                final Materia materiaConSituacionFinal = profesor.asignarSituacionMateria(materia, situacion);
                Optional<Materia> materiaExistente = alumnos
                        .stream()
                        .flatMap(data -> data.verMateriasInscriptas().stream())
                        .filter(data -> data == materiaConSituacionFinal).findFirst();

                materiaExistente.ifPresent(data -> {
                    int index = alumno.verMateriasInscriptas().indexOf(materiaConSituacionFinal);
                    alumno.verMateriasInscriptas().set(index, materiaConSituacionFinal);
                });

                System.out.println(alumno);

                JOptionPane.showMessageDialog(component, "Situacion de materia registrada exitosamente. 👈(ﾟヮﾟ👈)", "Situacion de Materia Final", JOptionPane.INFORMATION_MESSAGE);
                return alumno;
            } catch (ControlDeInscripcion ex) {
                new ErrorHandler().errorControlDeInscripciones(ex);
            }
        } else {
            JOptionPane.showMessageDialog(component, "Operación cancelada.", "Situacion de Materia Final", JOptionPane.INFORMATION_MESSAGE);
        }
        return null;

    }

}
