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

        // Valida los parámetros de entrada (tipo, número de turno, y fecha)
        Validaciones.validarString(tipo, "No puede estar vacío");
        Validaciones.validarNumero(numero, "No puede estar vacío");
        Validaciones.validarDate(fecha, "No puede estar vacío");

        // Determina el tipo de turno (espera o atendido)
        Turnos.tipoTurno tipoTurno = tipo.equalsIgnoreCase("espera") ? Turnos.tipoTurno.espera : Turnos.tipoTurno.atendido;

        // Crea un nuevo objeto Turnos
        Turnos turno = new Turnos();
        turno.setEstado(tipoTurno);
        turno.setFecha(fecha);
        turno.setNumero(numero);
        turno.setCandidatos(candidato);

        // Guarda el turno en la base de datos utilizando el repositorio genérico
        turnosJPA.createGenerico(turno);
    }


    public Optional<List<Turnos>> filtrarTurnos (Date fecha, String estado) throws ExcepcionPersonalizada {

        // Valida los parámetros de entrada
        Validaciones.validarDate(fecha, "No puede estar vacío");
        Validaciones.validarString(estado, "No puede estar vacío");

        // Filtra la lista de turnos según la fecha y el estado proporcionado
        List<Turnos> listaDeTurnos = turnosJPA.findAllGenerico()
                .stream()
                .filter(turno -> !turno.getFecha().before(fecha) && turno.getEstado().name().equalsIgnoreCase(estado))
                .toList();

        // Valida que la lista de turnos no esté vacía
        Validaciones.validarLista(listaDeTurnos, "No se encontraron turnos.");

        // Devuelve la lista de turnos filtrados envuelta en un Optional
        return Optional.of(listaDeTurnos);
    }

    public boolean existeNumeroTurno(int numero) {
            // Obtiene todos los turnos existentes
            List<Turnos> turnosExistentes = turnosJPA.findAllGenerico();

            // Verifica si algún turno tiene el mismo número que el proporcionado
            return turnosExistentes.stream()
                    .anyMatch(turno -> turno.getNumero() == numero);   // Verifica si ya existe el número
    }
}
