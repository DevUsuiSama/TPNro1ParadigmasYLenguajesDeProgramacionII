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

import com.systemengineering.tp1paradigmaylenguajesdeprogramacionii.models.Alumno;
import com.systemengineering.tp1paradigmaylenguajesdeprogramacionii.models.Carrera;
import com.systemengineering.tp1paradigmaylenguajesdeprogramacionii.models.Materia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author José Fernando Usui y Luciana Rojas
 */
public class ListarAlumnosController {

    private final Component component;
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private final List<Alumno> alumnos = new ArrayList<>();
    private final List<Carrera> carreras = new ArrayList<>();

    public ListarAlumnosController(List<Alumno> alumnos, List<Carrera> carreras, Component component) {
        this.alumnos.addAll(alumnos);
        this.carreras.addAll(carreras);
        this.component = component;
    }

    public void mostrar() {
        Object[] opciones = {"Listar Alumnos", "Cancelar"};

        JComboBox<String> comboCarreras = new JComboBox<>(carreras.stream().map((data) -> {
            return data.getNombre() + " | Duración: " + data.getDuracion();
        }).toArray(String[]::new));
        comboCarreras.setFont(new java.awt.Font("Segoe UI", 1, 14));
        comboCarreras.setAlignmentX(JComboBox.LEFT_ALIGNMENT);

        JComboBox<String> comboMaterias = new JComboBox<>(new String[]{"-- Recuerde, hay que seleccionar una carrera para mostrar la materia --"});
        comboMaterias.setFont(new java.awt.Font("Segoe UI", 1, 14));
        comboMaterias.setAlignmentX(JComboBox.LEFT_ALIGNMENT);

        comboCarreras.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboCarreras.getSelectedIndex() != -1) {
                    Carrera carrera = carreras.get(comboCarreras.getSelectedIndex());
                    comboMaterias.removeAllItems();
                    comboMaterias.setModel(
                            new DefaultComboBoxModel<>(
                                    carrera.getMaterias().stream().map((data) -> {
                                        return "Nombre: " + data.getNombre() + " | Anio: " + data.getAnio() + " | Profesor: " + data.getProfesor().getNombre() + " " + data.getProfesor().getApellido();
                                    }).toArray(String[]::new)
                            )
                    );
                }
            }

        });

        JLabel labelCarreras = new JLabel("Seleccionar una Carrera:");
        labelCarreras.setFont(new java.awt.Font("Segoe UI", 1, 14));
        labelCarreras.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        JLabel labelMaterias = new JLabel("Seleccionar una Materia:");
        labelCarreras.setFont(new java.awt.Font("Segoe UI", 1, 14));
        labelCarreras.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(labelCarreras);
        panel.add(comboCarreras);
        panel.add(Box.createVerticalStrut(5));
        panel.add(labelMaterias);
        panel.add(comboMaterias);

        int resultado = JOptionPane.showOptionDialog(
                component,
                panel,
                "Listar Alumnos",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                opciones,
                opciones[0]);

        if (resultado == JOptionPane.YES_NO_OPTION) {
            if (comboMaterias.getSelectedItem().equals("-- Recuerde, hay que seleccionar una carrera para mostrar la materia --")) {
                JOptionPane.showMessageDialog(component, "Debe seleccionar una carrera y luego seleccionar una materia (╯°□°）╯︵ ┻━┻.", "Listar Alumnos", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Carrera carrera = carreras.get(comboCarreras.getSelectedIndex());

            List<Alumno> listaControlCarrera = alumnos.stream().filter(data -> data.verCarreraMatriculado() == carrera).collect(Collectors.toList());

            if (listaControlCarrera.isEmpty()) {
                JOptionPane.showMessageDialog(component, "No hay alumnos matriculados en la carrera de \"" + carrera.getNombre() + "\"", "Listar Alumnos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Materia materia = carreras.get(comboCarreras.getSelectedIndex()).getMaterias().get(comboMaterias.getSelectedIndex());

            List<Alumno> listaControlMateria = alumnos.stream().filter(
                    data -> data.verMateriasInscriptas().stream().filter(
                            data2 -> data2 == materia
                    ).findFirst().isPresent()
            ).collect(Collectors.toList());

            if (listaControlMateria.isEmpty()) {
                JOptionPane.showMessageDialog(component, "No hay alumnos inscritos a la materia de \"" + materia.getNombre() + "\"", "Listar Alumnos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            StringBuilder listarAlumnos = new StringBuilder();

            for (Alumno alumno : alumnos) {
                if (alumno.verCarreraMatriculado() != null) {
                    if (alumno.verMateriasInscriptas() != null) {
                        listarAlumnos
                                .append(alumno.getNroLegajo())
                                .append(" - ")
                                .append(alumno.getNombre())
                                .append(" ")
                                .append(alumno.getApellido())
                                .append(" | Materia: ")
                                .append(alumno.verMateriasInscriptas().stream().filter(data -> {
                                    String materiaFormateado = "Nombre: " + data.getNombre() + " | Anio: " + data.getAnio() + " | Profesor: " + data.getProfesor().getNombre() + " " + data.getProfesor().getApellido();
                                    return materiaFormateado.equals(comboMaterias.getSelectedItem());
                                }).findFirst().orElse(null).getNombre())
                                .append(" | Situación Final: ")
                                .append(alumno.verMateriasInscriptas().stream().filter(data -> {
                                    String materiaFormateado = "Nombre: " + data.getNombre() + " | Anio: " + data.getAnio() + " | Profesor: " + data.getProfesor().getNombre() + " " + data.getProfesor().getApellido();
                                    return materiaFormateado.equals(comboMaterias.getSelectedItem());
                                }).findFirst().orElse(null).getSituacion())
                                .append("\n");
                    }
                }
            }

            JOptionPane.showMessageDialog(component, listarAlumnos.toString(), "Listar Alumnos", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(component, "Operación cancelada.", "Listar Alumnos", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
