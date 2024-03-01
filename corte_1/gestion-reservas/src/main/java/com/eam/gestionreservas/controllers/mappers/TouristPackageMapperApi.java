package com.eam.gestionreservas.controllers.mappers;

import com.eam.gestionreservas.controllers.request.TouristPackageRequest;
import com.eam.gestionreservas.controllers.response.TouristPackageResponse;
import com.eam.gestionreservas.models.domains.TouristPackage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class TouristPackageMapperApi {

    private final DestinationMapperApi destinationMapperApi;

    public TouristPackageMapperApi(DestinationMapperApi destinationMapperApi) {
        this.destinationMapperApi = destinationMapperApi;
    }

    public TouristPackage requestToDomain(TouristPackageRequest source){
        if( source == null){
            return null;
        }
        TouristPackage touristPackage = new TouristPackage();
        touristPackage.setCupoMaximoPersonas(source.getCupoMaximoPersonas());
        touristPackage.setNombre(source.getNombre());
        touristPackage.setId(source.getId());
        touristPackage.setPrecio(source.getPrecio());
        touristPackage.setDuracion(source.getDuracion());
        touristPackage.setFechaInicio(source.getFechaInicio());
        touristPackage.setDestinationList(destinationMapperApi.destinationRequestsToDomains(source.getDestinationList()));
        return touristPackage;
    }

    public TouristPackageResponse domainToResponse(TouristPackage source){
        if( source == null){
            return null;
        }
        TouristPackageResponse touristPackageResponse = new TouristPackageResponse();
        touristPackageResponse.setCupoMaximoPersonas(source.getCupoMaximoPersonas());
        touristPackageResponse.setNombre(source.getNombre());
        touristPackageResponse.setId(source.getId());
        touristPackageResponse.setPrecio(source.getPrecio());
        touristPackageResponse.setDuracion(source.getDuracion());
        touristPackageResponse.setFechaInicio(source.getFechaInicio());
        touristPackageResponse.setDestinationList(destinationMapperApi.domainToDestinationResponses(source.getDestinationList()));
        return touristPackageResponse;
    }

    public List<TouristPackageResponse> domainsToResponses(List<TouristPackage> touristPackages){
        if(touristPackages.isEmpty()){
            return Collections.emptyList();
        }
        List<TouristPackageResponse> touristPackageResponses = new ArrayList<>(touristPackages.size());
        for(TouristPackage touristPackage : touristPackages){
            touristPackageResponses.add(domainToResponse(touristPackage));
        }
        return touristPackageResponses;
    }
}
