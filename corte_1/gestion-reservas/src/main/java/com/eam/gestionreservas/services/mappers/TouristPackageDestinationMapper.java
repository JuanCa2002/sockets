package com.eam.gestionreservas.services.mappers;

import com.eam.gestionreservas.models.domains.TouristPackageDestination;
import com.eam.gestionreservas.models.entities.TouristPackageDestinationEntity;
import com.eam.gestionreservas.models.entities.TouristPackageEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TouristPackageDestinationMapper {

    private final TouristPackageMapper touristPackageMapper;

    private final DestinationMapper destinationMapper;

    public TouristPackageDestinationMapper(TouristPackageMapper touristPackageMapper, DestinationMapper destinationMapper) {
        this.touristPackageMapper = touristPackageMapper;
        this.destinationMapper = destinationMapper;
    }

    public List<TouristPackageDestination> entitiesToDomains(List<TouristPackageDestinationEntity> touristPackageDestinationEntities){
        if( touristPackageDestinationEntities == null){
            return null;
        }
        List<TouristPackageDestination> touristPackageDestinations = new ArrayList<>(touristPackageDestinationEntities.size());
        for(TouristPackageDestinationEntity touristPackageDestinationEntity: touristPackageDestinationEntities){
            touristPackageDestinations.add(entityToDomain(touristPackageDestinationEntity));
        }
        return touristPackageDestinations;
    }

    public List<TouristPackageDestinationEntity> domainsToEntities(List<TouristPackageDestination> touristPackageDestinations){
        if( touristPackageDestinations == null){
            return null;
        }
        List<TouristPackageDestinationEntity> touristPackageDestinationEntities = new ArrayList<>(touristPackageDestinations.size());
        for(TouristPackageDestination touristPackageDestination: touristPackageDestinations){
            touristPackageDestinationEntities.add(domainToEntity(touristPackageDestination));
        }
        return touristPackageDestinationEntities;
    }

    public TouristPackageDestination entityToDomain(TouristPackageDestinationEntity source){
        if(source == null){
            return null;
        }
        TouristPackageDestination touristPackageDestination = new TouristPackageDestination();
        touristPackageDestination.setId(source.getId());
        touristPackageDestination.setDestination(destinationMapper.entityToDomain(source.getDestination()));
        touristPackageDestination.setTouristPackage(touristPackageMapper.entityToDomain(source.getTouristPackage()));
        return touristPackageDestination;
    }

    public TouristPackageDestinationEntity domainToEntity(TouristPackageDestination source){
        if(source == null){
            return null;
        }
        TouristPackageDestinationEntity touristPackageDestinationEntity = new TouristPackageDestinationEntity();
        touristPackageDestinationEntity.setId(source.getId());
        touristPackageDestinationEntity.setDestination(destinationMapper.domainToEntity(source.getDestination()));
        touristPackageDestinationEntity.setTouristPackage(touristPackageMapper.domainToEntity(source.getTouristPackage()));
        return touristPackageDestinationEntity;
    }
}
