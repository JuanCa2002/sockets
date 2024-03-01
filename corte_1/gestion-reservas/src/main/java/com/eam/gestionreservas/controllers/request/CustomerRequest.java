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
public class CustomerRequest {

    private Integer id;

    @NotNull
    private String nombre;

    @NotNull
    private String cedula;

    @NotNull
    private String direccion;

    @NotNull
    private String correo;

    @NotNull
    private String telefono;
}
