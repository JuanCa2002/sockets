package com.eam.gestionreservas.exceptions;

public class TouristPackageNotFoundException extends RuntimeException{

    public TouristPackageNotFoundException(Integer id){
        super("No se encontro un paquete turistico con el id " + id);
    }
}
