package com.eam.agencia.models;

import java.io.Serializable;

public class Cliente implements Serializable {
    private Integer id;

    private String nombre;

    private String identificacion;

    private String direccion;

    private String telefono;

    private String email;

    public Cliente(Integer id, String nombre, String identificacion, String direccion, String telefono, String email) {
        this.id = id;
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return  "   Información cliente" + "\n"+
                "   --------------------" + "\n"+
                "   Id: " + id + "\n"+
                "   Nombre: " + nombre + "\n"+
                "   Identificación: " + identificacion + "\n"+
                "   Dirección: " + direccion + "\n"+
                "   Telefono: " + telefono + "\n"+
                "   Email: " + email;
    }
}
