package com.eam.gestionreservas.controllers.mappers;

import com.eam.gestionreservas.controllers.request.ReservationRequest;
import com.eam.gestionreservas.controllers.response.ReservationResponse;
import com.eam.gestionreservas.models.domains.Customer;
import com.eam.gestionreservas.models.domains.Reservation;
import com.eam.gestionreservas.models.domains.TouristPackage;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapperApi {

    private final TouristPackageMapperApi touristPackageMapperApi;

    private final CustomerMapperApi customerMapperApi;

    public ReservationMapperApi(TouristPackageMapperApi touristPackageMapperApi, CustomerMapperApi customerMapperApi) {
        this.touristPackageMapperApi = touristPackageMapperApi;
        this.customerMapperApi = customerMapperApi;
    }

    public Reservation requestToDomain(ReservationRequest source){
        if(source == null){
            return null;
        }
        Reservation reservation = new Reservation();
        reservation.setId(source.getId());
        reservation.setNumeroPersonas(source.getNumeroPersonas());
        TouristPackage touristPackage = new TouristPackage();
        touristPackage.setId(source.getTouristPackageId());
        Customer customer = new Customer();
        customer.setId(source.getCustomerId());
        reservation.setTouristPackage(touristPackage);
        reservation.setCustomer(customer);
        return reservation;
    }

    public ReservationResponse domainToResponse(Reservation source){
        if(source == null){
            return null;
        }
        ReservationResponse reservationResponse = new ReservationResponse();
        reservationResponse.setId(source.getId());
        reservationResponse.setNumeroPersonas(source.getNumeroPersonas());
        reservationResponse.setFechaReserva(source.getFechaReserva());
        reservationResponse.setTouristPackageResponse(touristPackageMapperApi.domainToResponse(source.getTouristPackage()));
        reservationResponse.setCustomer(customerMapperApi.domainToResponse(source.getCustomer()));
        return reservationResponse;
    }
}
