package com.eam.gestionreservas.services.mappers;

import com.eam.gestionreservas.models.domains.TouristPackage;
import com.eam.gestionreservas.models.entities.TouristPackageEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class TouristPackageMapper {

    public TouristPackage entityToDomain(TouristPackageEntity source){
        if(source == null){
            return null;
        }
        TouristPackage touristPackage = new TouristPackage();
        touristPackage.setId(source.getId());
        touristPackage.setNombre(source.getNombre());
        touristPackage.setDuracion(source.getDuracion());
        touristPackage.setPrecio(source.getPrecio());
        touristPackage.setFechaInicio(source.getFechaInicio());
        touristPackage.setCupoMaximoPersonas(source.getCupoMaximoPersonas());
        return touristPackage;
    }

    public TouristPackageEntity domainToEntity(TouristPackage source){
        if(source == null){
            return null;
        }
        TouristPackageEntity touristPackageEntity = new TouristPackageEntity();
        touristPackageEntity.setId(source.getId());
        touristPackageEntity.setNombre(source.getNombre());
        touristPackageEntity.setDuracion(source.getDuracion());
        touristPackageEntity.setPrecio(source.getPrecio());
        touristPackageEntity.setFechaInicio(source.getFechaInicio());
        touristPackageEntity.setCupoMaximoPersonas(source.getCupoMaximoPersonas());
        return touristPackageEntity;
    }

    public List<TouristPackageEntity> domainsToEntities(List<TouristPackage> touristPackages){
        if(touristPackages == null){
            return null;
        }
        List<TouristPackageEntity> entities = new ArrayList<>(touristPackages.size());
        for(TouristPackage touristPackage : touristPackages){
            entities.add(domainToEntity(touristPackage));
        }
        return entities;
    }

    public List<TouristPackage> entitiesToDomains(List<TouristPackageEntity> entities){
        if(entities == null){
            return Collections.emptyList();
        }
        List<TouristPackage> touristPackages = new ArrayList<>(entities.size());
        for(TouristPackageEntity touristPackageEntity: entities){
            touristPackages.add(entityToDomain(touristPackageEntity));
        }
        return touristPackages;
    }
}
