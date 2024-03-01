package com.eam.gestionreservas.controllers.response;

import com.eam.gestionreservas.controllers.request.DestinationRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class TouristPackageResponse {

    private Integer id;

    private String nombre;

    private LocalDate fechaInicio;

    private Integer duracion;

    private Integer cupoMaximoPersonas;

    private Long precio;

    private List<DestinationResponse> destinationList;

}
