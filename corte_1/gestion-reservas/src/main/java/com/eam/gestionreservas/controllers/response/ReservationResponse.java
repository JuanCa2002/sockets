package com.eam.gestionreservas.controllers.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReservationResponse {

    private Integer id;

    private Integer numeroPersonas;

    private LocalDate fechaReserva;

    private CustomerResponse customer;

    private TouristPackageResponse touristPackageResponse;

}
