package com.example.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Candidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "candidatos", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Turnos> turnos = new HashSet<>();

    public Candidato() {
    }

    public Candidato(String apellido, Long id, String nombre) {
        this.apellido = apellido;
        this.id = id;
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Turnos> getTurnos() {
        return turnos;
    }

    public void setTurnos(Set<Turnos> turnos) {
        this.turnos = turnos;
    }


    @Override
    public String toString() {
        return "Candidato{" +
                "apellido='" + apellido + '\'' +
                ", id=" + id +
                ", nombre='" + nombre + '\'' +
                ", turnos=" + turnos +
                '}';
    }
}
