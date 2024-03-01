package com.eam.gestionreservas.controllers;

import com.eam.gestionreservas.exceptions.BusinessException;
import com.eam.gestionreservas.exceptions.CustomerNotFoundException;
import com.eam.gestionreservas.exceptions.IntegritySqlException;
import com.eam.gestionreservas.exceptions.TouristPackageNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IntegritySqlException.class)
    public ResponseEntity<String> handleIntegritySqlException(IntegritySqlException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(TouristPackageNotFoundException.class)
    public ResponseEntity<String> handleTouristPackageNotFoundException(TouristPackageNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handleCustomerNotFoundException(CustomerNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handleBusinessException(BusinessException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
