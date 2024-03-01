package com.eam.gestionreservas.repositories;

import com.eam.gestionreservas.models.entities.TouristPackageDestinationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TouristPackageDestinationRepository extends JpaRepository<TouristPackageDestinationEntity, Integer> {

    @Query("SELECT tpd FROM TouristPackageDestinationEntity tpd WHERE tpd.touristPackage.id = :touristPackageId")
    List<TouristPackageDestinationEntity> findByTouristPackageId(@Param("touristPackageId") Integer touristPackageId);
}
