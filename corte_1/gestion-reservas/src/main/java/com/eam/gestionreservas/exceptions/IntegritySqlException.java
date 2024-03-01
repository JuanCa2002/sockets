package com.eam.gestionreservas.exceptions;

import org.apache.coyote.BadRequestException;

public class IntegritySqlException extends BadRequestException {

    private static final long serialVersionUID = 405653293282818171L;

    public IntegritySqlException(String code) {
        super(code);
    }

}
