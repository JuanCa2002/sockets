package com.eam.gestionreservas.services.mappers;

import com.eam.gestionreservas.models.domains.Destination;
import com.eam.gestionreservas.models.entities.DestinationEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DestinationMapper {

    public Destination entityToDomain(DestinationEntity source){
        if(source == null){
            return null;
        }
        Destination destination = new Destination();
        destination.setId(source.getId());
        destination.setImagen(source.getImagen());
        destination.setNombre(source.getNombre());
        return destination;
    }

    public List<Destination> entitiesToDomains(List<DestinationEntity> destinationEntities){
        if( destinationEntities == null){
            return null;
        }
        List<Destination> destinations = new ArrayList<>(destinationEntities.size());
        for( DestinationEntity destinationEntity: destinationEntities){
            destinations.add(entityToDomain(destinationEntity));
        }
        return destinations;
    }

    public List<DestinationEntity> domainsToEntities(List<Destination> destinations){
        if( destinations == null){
            return null;
        }
        List<DestinationEntity> entities = new ArrayList<>(destinations.size());
        for( Destination destination: destinations){
            entities.add(domainToEntity(destination));
        }
        return entities;
    }

    public DestinationEntity domainToEntity(Destination source){
        if(source == null){
            return null;
        }
        DestinationEntity destinationEntity = new DestinationEntity();
        destinationEntity.setId(source.getId());
        destinationEntity.setImagen(source.getImagen());
        destinationEntity.setNombre(source.getNombre());
        return destinationEntity;
    }
}
