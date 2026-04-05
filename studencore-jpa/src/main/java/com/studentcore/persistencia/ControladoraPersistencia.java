package com.studentcore.persistencia;

import com.studentcore.Alumno;
import java.util.List;

public class ControladoraPersistencia {

    // Instancia del controlador específico de Alumno
    private AlumnoJpaController aluJpa = new AlumnoJpaController();

    // Aquí irán los métodos para llamar al CRUD de Alumno
    public void crearAlumno(com.studentcore.Alumno alu) {
        aluJpa.create(alu);
    }

    public List<Alumno> traerAlumnos() {
        return aluJpa.findAlumnoEntities();
    }

    public void editarAlmno(Alumno alu) {
        aluJpa.edit(alu);
    }

    public Alumno traerAlumno(int id) {
        return aluJpa.findAlumno(id);
    }

    public void borrarAlumno(int id) {
        aluJpa.destroy(id);
    }
}