package com.example.entities;

import jakarta.persistence.*;

import java.util.Date;


@Entity
public class Turnos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int numero;

    @Column(nullable = false, columnDefinition = "DATE")
    private Date fecha;

    @Column(nullable = false)
    private tipoTurno estado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "candidato_id")
    private Candidato candidatos;

    public enum tipoTurno {
        espera,
        atendido

    }

    public Turnos() {
    }

    public Turnos( Date fecha, Long id, int numero) {
        this.fecha = fecha;
        this.id = id;
        this.numero = numero;
    }

    public Candidato getCandidatos() {
        return candidatos;
    }

    public void setCandidatos(Candidato candidatos) {
        this.candidatos = candidatos;
    }

    public tipoTurno getEstado() {
        return estado;
    }

    public void setEstado(tipoTurno estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Turnos{" +
                "candidatos=" + candidatos +
                ", id=" + id +
                ", numero=" + numero +
                ", fecha=" + fecha +
                ", estado=" + estado +
                '}';
    }
}
