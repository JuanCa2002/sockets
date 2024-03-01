package com.eam.gestionreservas.controllers;

import com.eam.gestionreservas.controllers.mappers.CustomerMapperApi;
import com.eam.gestionreservas.controllers.mappers.DestinationMapperApi;
import com.eam.gestionreservas.controllers.mappers.ReservationMapperApi;
import com.eam.gestionreservas.controllers.mappers.TouristPackageMapperApi;
import com.eam.gestionreservas.controllers.request.ReservationRequest;
import com.eam.gestionreservas.controllers.response.ReservationResponse;
import com.eam.gestionreservas.models.domains.Reservation;
import com.eam.gestionreservas.services.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("${request-mapping.controller.reservation}")
public class ReservationController {

    private final CustomerMapperApi customerMapperApi = new CustomerMapperApi();

    private final DestinationMapperApi destinationMapperApi = new DestinationMapperApi();

    private final TouristPackageMapperApi touristPackageMapperApi = new TouristPackageMapperApi(destinationMapperApi);

    private final ReservationMapperApi reservationMapperApi = new ReservationMapperApi(touristPackageMapperApi, customerMapperApi);

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationResponse> save(@RequestBody @Valid ReservationRequest request){
        Reservation reservation = reservationService.save(reservationMapperApi.requestToDomain(request));
        return new ResponseEntity<>(reservationMapperApi.domainToResponse(reservation), HttpStatus.CREATED);
    }
}
