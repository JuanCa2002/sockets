package com.eam.agencia.exceptions;

public class NotBlankFieldException extends RuntimeException{
    public NotBlankFieldException(){
       super("Los campos no pueden ir en blanco, porfavor rectifica");
    }

}
