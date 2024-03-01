package com.eam.gestionreservas.services.mappers;

import com.eam.gestionreservas.models.domains.Reservation;
import com.eam.gestionreservas.models.entities.ReservationEntity;
import org.springframework.stereotype.Component;

import javax.swing.text.html.parser.Entity;

@Component
public class ReservationMapper {

    private final TouristPackageMapper touristPackageMapper;

    private final CustomerMapper customerMapper;

    public ReservationMapper(TouristPackageMapper touristPackageMapper, CustomerMapper customerMapper) {
        this.touristPackageMapper = touristPackageMapper;
        this.customerMapper = customerMapper;
    }

    public Reservation entityToDomain(ReservationEntity source){
        if(source == null){
            return null;
        }
        Reservation reservation = new Reservation();
        reservation.setId(source.getId());
        reservation.setFechaReserva(source.getFechaReserva());
        reservation.setNumeroPersonas(source.getNumeroPersonas());
        reservation.setTouristPackage(touristPackageMapper.entityToDomain(source.getTouristPackage()));
        reservation.setCustomer(customerMapper.entityToDomain(source.getCustomer()));
        return reservation;
    }

    public ReservationEntity domainToEntity(Reservation source){
        if(source == null){
            return null;
        }
        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setId(source.getId());
        reservationEntity.setFechaReserva(source.getFechaReserva());
        reservationEntity.setNumeroPersonas(source.getNumeroPersonas());
        reservationEntity.setTouristPackage(touristPackageMapper.domainToEntity(source.getTouristPackage()));
        reservationEntity.setCustomer(customerMapper.domainToEntity(source.getCustomer()));
        return reservationEntity;
    }
}
