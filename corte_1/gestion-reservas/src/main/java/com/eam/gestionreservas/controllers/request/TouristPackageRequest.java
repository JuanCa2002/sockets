package com.eam.gestionreservas.controllers.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TouristPackageRequest {

    private Integer id;

    @NotNull
    private String nombre;

    @NotNull
    private LocalDate fechaInicio;

    @NotNull
    private Integer duracion;

    @NotNull
    private Integer cupoMaximoPersonas;

    @NotNull
    private Long precio;

    @NotNull
    private List<DestinationRequest> destinationList;
}
