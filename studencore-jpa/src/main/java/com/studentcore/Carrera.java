package com.studentcore;

import java.util.List;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Carrera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String nombre;

    // Aquí le decimos que una Carrera tiene una LISTA de Alumnos
    // mappedBy dice quién es el "dueño" de la relación en la otra clase

    @OneToMany(mappedBy = "carre")

    private List<Alumno> listaAlumnos;

    public Carrera() {

    }

    public Carrera(int id, String nombre) {

        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}