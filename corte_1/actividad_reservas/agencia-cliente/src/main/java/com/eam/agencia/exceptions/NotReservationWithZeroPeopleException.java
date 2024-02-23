package com.eam.agencia.exceptions;

public class NotReservationWithZeroPeopleException extends RuntimeException{
    public NotReservationWithZeroPeopleException(){
        super("No se puede realizar una reservación con cero persona o numero negativo, verifica");
    }
}
