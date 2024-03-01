package com.eam.gestionreservas.repositories;

import com.eam.gestionreservas.models.entities.DestinationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationRepository extends JpaRepository<DestinationEntity, Integer> {
}
