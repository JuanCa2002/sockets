package com.eam.gestionreservas.models.domains;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class TouristPackage {

    private Integer id;

    private String nombre;

    private LocalDate fechaInicio;

    private Integer duracion;

    private Integer cupoMaximoPersonas;

    private Long precio;

    private List<Destination> destinationList;
}
