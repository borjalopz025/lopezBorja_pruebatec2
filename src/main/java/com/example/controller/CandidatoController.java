package com.example.controller;

import com.example.entities.Candidato;
import com.example.entities.Turnos;
import com.example.exceptions.ExcepcionPersonalizada;
import com.example.persistence.GenericoJPA;
import com.example.utils.Validaciones;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class CandidatoController {

    private final GenericoJPA<Candidato, Long> candidatoJPA;

    public CandidatoController() {
        this.candidatoJPA = new GenericoJPA<>(Candidato.class);
    }

    public void create(String nombre, String apellido) throws ExcepcionPersonalizada {

        Validaciones.validarString(nombre, "El nombre no puede estar vacío.");
        Validaciones.validarString(apellido, "El apellido no puede estar vacío.");

        Candidato persona = new Candidato();
        persona.setNombre(nombre);
        persona.setApellido(apellido);
        candidatoJPA.createGenerico(persona);
    }

    public List<Candidato> listaCandidatos () throws ExcepcionPersonalizada {

        List<Candidato> candidatos = candidatoJPA.findAllGenerico();

        Validaciones.validarLista(candidatos, "No se encontraron candidatos.");

        return candidatos;
    }




}
