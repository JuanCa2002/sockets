package com.eam.gestionreservas.models.domains;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {

    private Integer id;

    private String nombre;

    private String cedula;

    private String direccion;

    private String correo;

    private String telefono;
}
