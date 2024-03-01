package com.eam.gestionreservas.controllers.request;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequest {

    private Integer id;

    @NotNull
    private Integer numeroPersonas;

    @NotNull
    private Integer touristPackageId;

    @NotNull
    private Integer customerId;
}
