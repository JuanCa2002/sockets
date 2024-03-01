package com.eam.gestionreservas.repositories;

import com.eam.gestionreservas.models.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
}
