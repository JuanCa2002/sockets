package com.eam.gestionreservas.repositories;

import com.eam.gestionreservas.models.entities.TouristPackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TouristPackageRepository extends JpaRepository<TouristPackageEntity, Integer> {
}
