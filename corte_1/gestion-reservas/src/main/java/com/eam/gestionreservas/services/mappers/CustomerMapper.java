package com.eam.gestionreservas.services.mappers;

import com.eam.gestionreservas.models.domains.Customer;
import com.eam.gestionreservas.models.entities.CustomerEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer entityToDomain(CustomerEntity source){
        if(source == null){
            return null;
        }
        Customer customer = new Customer();
        customer.setId(source.getId());
        customer.setCorreo(source.getCorreo());
        customer.setCedula(source.getCedula());
        customer.setNombre(source.getNombre());
        customer.setTelefono(source.getTelefono());
        customer.setDireccion(source.getDireccion());
        return customer;
    }

    public CustomerEntity domainToEntity(Customer source){
        if(source == null){
            return null;
        }
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(source.getId());
        customerEntity.setCorreo(source.getCorreo());
        customerEntity.setCedula(source.getCedula());
        customerEntity.setNombre(source.getNombre());
        customerEntity.setTelefono(source.getTelefono());
        customerEntity.setDireccion(source.getDireccion());
        return customerEntity;
    }
}
