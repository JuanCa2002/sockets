package com.eam.gestionreservas.controllers;

import com.eam.gestionreservas.controllers.mappers.CustomerMapperApi;
import com.eam.gestionreservas.controllers.request.CustomerRequest;
import com.eam.gestionreservas.controllers.response.CustomerResponse;
import com.eam.gestionreservas.exceptions.IntegritySqlException;
import com.eam.gestionreservas.models.domains.Customer;
import com.eam.gestionreservas.services.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("${request-mapping.controller.client}")
public class CustomerController {

    private final CustomerMapperApi customerMapperApi;

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponse> save(@RequestBody @Valid CustomerRequest customerRequest) throws IntegritySqlException {
          Customer customer = customerService.save(customerMapperApi.requestToDomain(customerRequest));
          return new ResponseEntity<>(customerMapperApi.domainToResponse(customer), HttpStatus.CREATED);
    }
}
