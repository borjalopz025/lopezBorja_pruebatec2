package com.example.controller;

import com.example.entities.Candidato;
import com.example.entities.Turnos;
import com.example.exceptions.ExcepcionPersonalizada;
import com.example.persistence.GenericoJPA;
import com.example.utils.Validaciones;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class TurnosController {

    private final GenericoJPA<Turnos, Long> turnosJPA;

    public TurnosController() {
        this.turnosJPA = new GenericoJPA<>(Turnos.class);
    }

    public void create(String tipo, Integer numero, Date fecha, Candidato candidato) throws ExcepcionPersonalizada {

        Validaciones.validarString(tipo," No puede estar vacio");
        Validaciones.validarNumero(numero, " No puede estar vacio");
        Validaciones.validarDate(fecha, " No puede estar vacio");

        Turnos.tipoTurno tipoTurno = tipo.equalsIgnoreCase("espera") ? Turnos.tipoTurno.espera : Turnos.tipoTurno.atendido;

        Turnos turno = new Turnos();
        turno.setEstado(tipoTurno);
        turno.setFecha(fecha);
        turno.setNumero(numero);
        turno.setCandidatos(candidato);
        turnosJPA.createGenerico(turno);
    }


    public Optional<List<Turnos>> filtrarTurnos (Date fecha, String estado) throws ExcepcionPersonalizada {

        Validaciones.validarDate(fecha, " No puede estar vacio");
        Validaciones.validarString(estado," No puede estar vacio");

        List<Turnos> listaDeTurnos = turnosJPA.findAllGenerico()
                .stream()
                .filter(turno -> !turno.getFecha().before(fecha) && turno.getEstado().name().equalsIgnoreCase(estado))
                .toList();

        Validaciones.validarLista(listaDeTurnos," No puede estar vacio");

        return Optional.of(listaDeTurnos);
    }
}
