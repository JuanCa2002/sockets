package com.eam.gestionreservas.models.domains;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Reservation {

    private Integer id;

    private Integer numeroPersonas;

    private LocalDate fechaReserva;

    private TouristPackage touristPackage;

    private Customer customer;
}
