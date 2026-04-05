package com.studentcore;

import com.studentcore.persistencia.ControladoraPersistencia;
import java.util.List;

public class App {
    public static void main(String[] args) {

        ControladoraPersistencia control = new ControladoraPersistencia();

        Alumno alu = new Alumno(0, "Juan", "Cruz");
        Alumno alu3 = new Alumno(0, "Mauro", "Cruz");
        control.crearAlumno(alu);
        control.crearAlumno(alu3);
        try {
            System.out.println("------------------------------");
            System.out.println("El alumno fue creado con exito");
            System.out.println("-------------------------------");
        } catch (Exception e) {
            System.out.println("Error al crear el alumno: " + e.getMessage());
        }

        List<Alumno> listaAlumnos = control.traerAlumnos();

        System.out.println("-------------------------------");
        System.out.println("Lista de alumnos");
        for (Alumno aluR : listaAlumnos) {
            System.out.println("El id es : " + aluR.getId() +
                    " El nombre es: " + aluR.getNombre() + " Apellido: " + aluR.getApellido());
            System.out.println("-------------------------------");
        }

        List<Alumno> listaAlumnos2 = control.traerAlumnos();
        Alumno aluU = listaAlumnos2.get(0);

        if (aluU != null) {
            System.out.println("-------------------------------");
            System.out.println("Alumno encontrado!: " + aluU.getNombre());
            System.out.println("-------------------------------");
            // Cambiamos el nombre en la memoria de Java
            aluU.setNombre("Diego");
            aluU.setApellido("Cruz");

            // Mnadamos el objeto a la DB
            control.editarAlmno(aluU);
            System.out.println("-------------------------------");
            System.out.println("Datos actualizados!");
            System.out.println("-------------------------------");
        } else {
            System.out.println("No existe alumno con ese ID");
        }

        // Hacemos el proceso para borrar el alumno
        control.borrarAlumno(1);

        System.out.println("El alumno se elimino correctamente!.");

        // Volvemos a listar y asi confimarmos que no esta
        System.out.println("Lista actualizada.");
        for (Alumno a : control.traerAlumnos()) {
            System.out.println(a.getNombre());
        }

    }
}
