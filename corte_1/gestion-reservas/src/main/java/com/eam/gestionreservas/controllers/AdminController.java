package com.eam.gestionreservas.controllers;

import com.eam.gestionreservas.controllers.mappers.DestinationMapperApi;
import com.eam.gestionreservas.controllers.mappers.TouristPackageMapperApi;
import com.eam.gestionreservas.controllers.request.TouristPackageRequest;
import com.eam.gestionreservas.controllers.response.TouristPackageResponse;
import com.eam.gestionreservas.exceptions.IntegritySqlException;
import com.eam.gestionreservas.models.domains.TouristPackage;
import com.eam.gestionreservas.services.TouristPackageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("${request-mapping.controller.admin}")
public class AdminController {

    private  final DestinationMapperApi destinationMapperApi = new DestinationMapperApi();

    private final TouristPackageMapperApi touristPackageMapperApi = new TouristPackageMapperApi(destinationMapperApi);

    private final TouristPackageService touristPackageService;

    @PostMapping
    public ResponseEntity<TouristPackageResponse> save(@RequestBody @Valid TouristPackageRequest touristPackageRequest) throws IntegritySqlException {
        TouristPackage touristPackage = touristPackageService.save(touristPackageMapperApi.requestToDomain(touristPackageRequest));
        return new ResponseEntity<>(touristPackageMapperApi.domainToResponse(touristPackage), HttpStatus.CREATED);
    }
}
