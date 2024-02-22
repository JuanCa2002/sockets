package com.eam.agencia.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PaqueteTuristico implements Serializable {

    private Integer id;

    private String nombre;

    private LocalDate fecha;

    private int duracion;

    private Long precio;

    private List<String> destino;

    public PaqueteTuristico(Integer id, String nombre, LocalDate fecha, int duracion, Long precio, List<String> destino) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.duracion = duracion;
        this.precio = precio;
        this.destino = destino;
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

    public List<String> getDestino() {
        return destino;
    }

    public void setDestino(List<String> destino) {
        this.destino = destino;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return  "Informacion paquete turistico N° " +id+ "\n"+
                "Nombre: " + nombre + "\n" +
                "Fecha: " + fecha.format(dateTimeFormatter) + "\n" +
                "Duración: " + duracion +" dias"+ "\n" +
                "Precio: $" + precio + "\n" +
                "Destino: " + String.join("-", destino) + "\n";
    }
}
