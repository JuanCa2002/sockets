package com.eam.gestionreservas.controllers;

import com.eam.gestionreservas.controllers.mappers.DestinationMapperApi;
import com.eam.gestionreservas.controllers.mappers.TouristPackageMapperApi;
import com.eam.gestionreservas.controllers.response.TouristPackageResponse;
import com.eam.gestionreservas.models.domains.TouristPackage;
import com.eam.gestionreservas.services.TouristPackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${request-mapping.controller.tourist-package}")
public class TouristPackageController {

    private  final DestinationMapperApi destinationMapperApi = new DestinationMapperApi();

    private final TouristPackageMapperApi touristPackageMapperApi = new TouristPackageMapperApi(destinationMapperApi);

    private final TouristPackageService touristPackageService;

    @GetMapping("/{id}")
    public ResponseEntity<TouristPackageResponse> findById(@PathVariable Integer id){
        TouristPackage touristPackage = touristPackageService.findById(id);
        return new ResponseEntity<>(touristPackageMapperApi.domainToResponse(touristPackage), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TouristPackageResponse>> findAll(){
        List<TouristPackage> touristPackages = touristPackageService.findAll();
        return new ResponseEntity<>(touristPackageMapperApi.domainsToResponses(touristPackages), HttpStatus.OK);
    }
}
