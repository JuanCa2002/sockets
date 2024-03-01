package com.eam.gestionreservas.repositories;

import com.eam.gestionreservas.models.entities.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Integer> {
}
