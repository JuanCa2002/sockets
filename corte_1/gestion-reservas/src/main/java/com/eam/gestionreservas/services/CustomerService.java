package com.eam.gestionreservas.services;

import com.eam.gestionreservas.exceptions.IntegritySqlException;
import com.eam.gestionreservas.models.domains.Customer;
import com.eam.gestionreservas.models.entities.CustomerEntity;
import com.eam.gestionreservas.repositories.CustomerRepository;
import com.eam.gestionreservas.services.mappers.CustomerMapper;
import com.eam.gestionreservas.utils.GenerateExceptionUtils;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerMapper customerMapper;

    private final CustomerRepository customerRepository;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = IntegritySqlException.class)
    public Customer save(Customer customer) throws IntegritySqlException {
        try{
            customer.setId(null);
            CustomerEntity savedCustomer = customerRepository.saveAndFlush(customerMapper.domainToEntity(customer));
            return customerMapper.entityToDomain(savedCustomer);
        }catch (DataIntegrityViolationException e){
            String constraintName = ((ConstraintViolationException)e.getCause()).getConstraintName();
            throw GenerateExceptionUtils.generateExceptionUtil(constraintName);
        }
    }

    @Transactional(readOnly = true)
    public Customer findById(Integer id){
        return customerMapper.entityToDomain(customerRepository.findById(id).orElse(null));
    }
}
