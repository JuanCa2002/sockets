package com.eam.gestionreservas.controllers.mappers;

import com.eam.gestionreservas.controllers.request.CustomerRequest;
import com.eam.gestionreservas.controllers.response.CustomerResponse;
import com.eam.gestionreservas.models.domains.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapperApi {

    public Customer requestToDomain(CustomerRequest source){
        if(source == null){
            return null;
        }
        Customer customer = new Customer();
        customer.setDireccion(source.getDireccion());
        customer.setNombre(source.getNombre());
        customer.setTelefono(source.getTelefono());
        customer.setCedula(source.getCedula());
        customer.setId(source.getId());
        customer.setCorreo(source.getCorreo());
        return customer;
    }

    public CustomerResponse domainToResponse(Customer source){
        if(source == null){
            return null;
        }
        CustomerResponse response = new CustomerResponse();
        response.setDireccion(source.getDireccion());
        response.setNombre(source.getNombre());
        response.setTelefono(source.getTelefono());
        response.setCedula(source.getCedula());
        response.setId(source.getId());
        response.setCorreo(source.getCorreo());
        return response;
    }

}
