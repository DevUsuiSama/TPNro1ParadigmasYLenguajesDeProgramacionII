/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.systemengineering.tp1paradigmaylenguajesdeprogramacionii.controllers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.systemengineering.tp1paradigmaylenguajesdeprogramacionii.models.Alumno;
import com.systemengineering.tp1paradigmaylenguajesdeprogramacionii.models.Carrera;
import com.systemengineering.tp1paradigmaylenguajesdeprogramacionii.models.Coordinador;
import com.systemengineering.tp1paradigmaylenguajesdeprogramacionii.models.Materia;
import com.systemengineering.tp1paradigmaylenguajesdeprogramacionii.models.Profesor;
import com.systemengineering.tp1paradigmaylenguajesdeprogramacionii.views.MenuView;

/**
 *
 * @author José Fernando Usui y Luciana Rojas
 */
public class MenuController {
    
    private final MenuView menuView = new MenuView(this);
    private final List<Alumno> alumnos = new ArrayList<>();
    private final List<Carrera> carreras = new ArrayList<>();
    private final List<Materia> materiasDeIngenieria = new ArrayList<>();
    private final List<Materia> materiasDeLicEnDormir = new ArrayList<>();
    private final List<Materia> materiasDeMetafisica = new ArrayList<>();
    private final List<Materia> materiasDeFilosofia = new ArrayList<>();
    private final LocalDate fecha[] = {
        LocalDate.of(1998, 5, 4),
        LocalDate.of(1945, 9, 20),
        LocalDate.of(1905, 12, 14),
        LocalDate.of(1856, 11, 2),
        LocalDate.of(1756, 8, 9)
    };
    private final Profesor profesores[] = {
        new Profesor("Fernando", "Usui", "Calle 123", Date.from(fecha[4].atStartOfDay(ZoneId.systemDefault()).toInstant())),
        new Profesor("Rojas", "Luciana", "Calle 456", Date.from(fecha[4].atStartOfDay(ZoneId.systemDefault()).toInstant()))
    };
    
    public MenuController() {
        alumnos.addAll(new ArrayList<Alumno>() {
            {
                add(new Alumno(1, "Jose", "Perez", "Calle 123", Date.from(fecha[0].atStartOfDay(ZoneId.systemDefault()).toInstant())));
                add(new Alumno(2, "Maria", "Gomez", "Calle 456", Date.from(fecha[1].atStartOfDay(ZoneId.systemDefault()).toInstant())));
                add(new Alumno(3, "Pedro", "Lopez", "Calle 789", Date.from(fecha[2].atStartOfDay(ZoneId.systemDefault()).toInstant())));
                add(new Alumno(4, "Susanaoria", "Kimoji", "Calle 789", Date.from(fecha[2].atStartOfDay(ZoneId.systemDefault()).toInstant())));
            }
        });

        materiasDeIngenieria.addAll(new ArrayList<Materia>() {
            {
                add(new Materia("Fisica I", 2, 1, "com B", profesores[0], null));
                add(new Materia("Teoria del Viento I", 4, 2, "com A", profesores[1], null));
                add(new Materia("Fisica II", 3, 2, "com C", profesores[0], null));
                add(new Materia("Practica I", 5, 1, "com A", profesores[1], null));
                add(new Materia("Practica II", 5, 2, "com A", profesores[0], null));
            }
        });

        materiasDeLicEnDormir.addAll(new ArrayList<Materia>() {
            {
                add(new Materia("Dormir I", 1, 1, "com B", profesores[0], null));
                add(new Materia("Dormir Boca Abajo I", 1, 2, "com A", profesores[1], null));
                add(new Materia("Dormir en la Ruta II", 2, 1, "com C", profesores[0], null));
                add(new Materia("Dormir en una redada policial III", 3, 2, "com A", profesores[1], null));
                add(new Materia("Dormir bajo fuego intenso IV", 4, 1, "com B", profesores[0], null));
                add(new Materia("Dormir a la deriva en mar adentro V", 5, 2, "com C", profesores[0], null));
            }
        });

        materiasDeMetafisica.addAll(new ArrayList<Materia>() {
            {
                add(new Materia("Metafisica I", 1, 1, "com B", profesores[0], null));
                add(new Materia("Metafisica II", 2, 2, "com A", profesores[1], null));
                add(new Materia("Metafisica III", 3, 1, "com C", profesores[0], null));
            }
        });

        materiasDeFilosofia.addAll(new ArrayList<Materia>() {
            {
                add(new Materia("Yerba Casera I", 1, 1, "com B", profesores[0], null));
                add(new Materia("Yerba Mística II", 2, 2, "com A", profesores[1], null));
                add(new Materia("Yerba Legendaria III", 3, 1, "com C", profesores[0], null));
            }
        });

        Coordinador coordinador = new Coordinador("Reviro", "Chipai", "Lomas de Zamora 1455", Date.from(fecha[3].atStartOfDay(ZoneId.systemDefault()).toInstant()));

        carreras.addAll(new ArrayList<Carrera>() {
            {
                add(new Carrera("Ingeniería Aeroespacial", 5,coordinador, 1250230.10, 560231.23, materiasDeIngenieria));
                add(new Carrera("Licenciatura del Dormir", 3, coordinador, 1250230.10, 560231.23, materiasDeLicEnDormir));
                add(new Carrera("Ingeniería Astral", 4, coordinador, 1250230.10, 560231.23, materiasDeIngenieria));
                add(new Carrera("Diplomado en Metafísica", 3, coordinador, 1250230.10, 560231.23, materiasDeMetafisica));
                add(new Carrera("Licenciatura en Filosofía (Sin salida laboral)", 3, coordinador, 1250230.10, 560231.23, materiasDeFilosofia));
            }
        });
    }
    
    public void iniciar() {
        menuView.setVisible(true);
    }
    
    public void matricular() {
        MatricularController matricularController = new MatricularController(alumnos, carreras, menuView);
        
        Alumno alumnoMatriculado = matricularController.guardar();

        if (alumnoMatriculado == null) return;
        
        Optional<Alumno> alumnoExistente = alumnos.stream()
                .filter(data -> data == alumnoMatriculado)
                .findFirst();

        alumnoExistente.ifPresent(data -> {
            int index = alumnos.indexOf(alumnoMatriculado);
            alumnos.set(index, alumnoMatriculado);
        });
    }

    public void inscribirse() {
        InscribirseController inscribirseController = new InscribirseController(alumnos, menuView);
        
        Alumno alumnoInscrito = inscribirseController.guardar();
        
        if (alumnoInscrito == null) return;
        
        Optional<Alumno> alumnoExistente = alumnos.stream()
                .filter(data -> data == alumnoInscrito)
                .findFirst();

        alumnoExistente.ifPresent(data -> {
            int index = alumnos.indexOf(alumnoInscrito);
            alumnos.set(index, alumnoInscrito);
        });
    }

    public void cargarSituacionFinal() {
        SituacionFinalController situacionFinalController = new SituacionFinalController(profesores, alumnos, menuView);

        Alumno alumnoConSituacionFinal = situacionFinalController.guardar();

        if (alumnoConSituacionFinal == null) return;
        
        Optional<Alumno> alumnoExistente = alumnos.stream()
                .filter(data -> data == alumnoConSituacionFinal)
                .findFirst();
        alumnoExistente.ifPresent(data -> {
            int index = alumnos.indexOf(alumnoConSituacionFinal);
            alumnos.set(index, alumnoConSituacionFinal);
        });
    }

    public void listarAlumnos() {
        ListarAlumnosController listarAlumnosController = new ListarAlumnosController(alumnos, carreras, menuView);
        listarAlumnosController.mostrar();
    }
    
}
