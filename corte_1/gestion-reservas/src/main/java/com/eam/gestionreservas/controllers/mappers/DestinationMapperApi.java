package com.eam.gestionreservas.controllers.mappers;

import com.eam.gestionreservas.controllers.request.DestinationRequest;
import com.eam.gestionreservas.controllers.response.DestinationResponse;
import com.eam.gestionreservas.models.domains.Destination;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DestinationMapperApi {

    private Destination requestToDomain(DestinationRequest source){
        if(source == null){
            return null;
        }
        Destination destination = new Destination();
        destination.setId(source.getId());
        return destination;
    }

    private DestinationResponse domainToResponse(Destination source){
        if(source == null){
            return null;
        }
        DestinationResponse destinationResponse = new DestinationResponse();
        destinationResponse.setId(source.getId());
        destinationResponse.setNombre(source.getNombre());
        destinationResponse.setImagen(source.getImagen());
        return destinationResponse;
    }

    public List<Destination> destinationRequestsToDomains(List<DestinationRequest> destinationRequestList){
        if(destinationRequestList == null){
            return null;
        }
        List<Destination> destinationList = new ArrayList<>(destinationRequestList.size());
        for(DestinationRequest destinationRequest : destinationRequestList){
            destinationList.add(requestToDomain(destinationRequest));
        }
        return destinationList;
    }

    public List<DestinationResponse> domainToDestinationResponses(List<Destination> destinationList){
        if(destinationList == null){
            return null;
        }
        List<DestinationResponse> destinationResponseListList = new ArrayList<>(destinationList.size());
        for(Destination destination : destinationList){
            destinationResponseListList.add(domainToResponse(destination));
        }
        return destinationResponseListList;
    }
}
