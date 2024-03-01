package com.eam.gestionreservas.controllers.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponse {

    private Integer id;

    private String nombre;

    private String cedula;

    private String direccion;

    private String correo;

    private String telefono;
}
