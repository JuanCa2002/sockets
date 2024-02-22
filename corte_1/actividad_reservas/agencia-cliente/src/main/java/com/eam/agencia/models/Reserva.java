package com.eam.agencia.models;

import java.io.Serializable;
import java.time.LocalDate;

public class Reserva implements Serializable {

    private Integer id;

    private Integer paqueteTuristicoId;

    private String identificacionCliente;

    private LocalDate fechaReserva;

    private int numeroPersonas;

    public Reserva(Integer id, Integer paqueteTuristicoId, String identificacionCliente, LocalDate fechaReserva, int numeroPersonas) {
        this.id = id;
        this.paqueteTuristicoId = paqueteTuristicoId;
        this.identificacionCliente = identificacionCliente;
        this.fechaReserva = fechaReserva;
        this.numeroPersonas = numeroPersonas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPaqueteTuristicoId() {
        return paqueteTuristicoId;
    }

    public void setPaqueteTuristicoId(Integer paqueteTuristicoId) {
        this.paqueteTuristicoId = paqueteTuristicoId;
    }

    public String getIdentificacionCliente() {
        return identificacionCliente;
    }

    public void setIdentificacionCliente(String identificacionCliente) {
        this.identificacionCliente = identificacionCliente;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public int getNumeroPersonas() {
        return numeroPersonas;
    }

    public void setNumeroPersonas(int numeroPersonas) {
        this.numeroPersonas = numeroPersonas;
    }
}
