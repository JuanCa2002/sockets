package com.eam.gestionreservas.exceptions;

public class CustomerNotFoundException extends RuntimeException{

    public CustomerNotFoundException(Integer id){
        super("No se encontro ningun cliente con el id "+ id);
    }
}
