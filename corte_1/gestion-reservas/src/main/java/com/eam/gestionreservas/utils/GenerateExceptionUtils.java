package com.eam.gestionreservas.utils;

import com.eam.gestionreservas.exceptions.IntegritySqlException;

public final class GenerateExceptionUtils {

    private static final String UNIQUE_CEDULA = "UNIQUE_CEDULA";

    private static final String UNIQUE_NOMBRE = "UNIQUE_NOMBRE";

    private static final String FK_DESTINO = "1452";

    public static IntegritySqlException generateExceptionUtil(String constraintName){
        IntegritySqlException exception = new IntegritySqlException("Ocurrio un error");
        if(constraintName.equals(UNIQUE_CEDULA)){
            exception = new IntegritySqlException("La cedula del cliente debe ser unica");
        }
        if(constraintName.contains(FK_DESTINO)){
            exception = new IntegritySqlException("Un destino de la lista no es valido");
        }
        if(constraintName.contains(UNIQUE_NOMBRE)){
            exception = new IntegritySqlException("El nombre del paquete turistico ya se encuentra creado");
        }
        return exception;
    }
}
