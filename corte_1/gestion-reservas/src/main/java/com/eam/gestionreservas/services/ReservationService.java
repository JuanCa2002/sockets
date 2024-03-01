package com.eam.gestionreservas.services;

import com.eam.gestionreservas.exceptions.BusinessException;
import com.eam.gestionreservas.exceptions.CustomerNotFoundException;
import com.eam.gestionreservas.exceptions.IntegritySqlException;
import com.eam.gestionreservas.exceptions.TouristPackageNotFoundException;
import com.eam.gestionreservas.models.domains.Customer;
import com.eam.gestionreservas.models.domains.Reservation;
import com.eam.gestionreservas.models.domains.TouristPackage;
import com.eam.gestionreservas.models.entities.ReservationEntity;
import com.eam.gestionreservas.repositories.ReservationRepository;
import com.eam.gestionreservas.services.mappers.CustomerMapper;
import com.eam.gestionreservas.services.mappers.ReservationMapper;
import com.eam.gestionreservas.services.mappers.TouristPackageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    private final CustomerMapper customerMapper = new CustomerMapper();

    private final TouristPackageMapper touristPackageMapper = new TouristPackageMapper();

    private final ReservationMapper reservationMapper = new ReservationMapper(touristPackageMapper, customerMapper);

    private final TouristPackageService touristPackageService;

    private final CustomerService customerService;

    @Transactional(propagation = Propagation.REQUIRED)
    public Reservation save(Reservation reservation){
        TouristPackage touristPackage = touristPackageService.findById(reservation.getTouristPackage().getId());
        if(touristPackage == null){
            throw new TouristPackageNotFoundException(reservation.getTouristPackage().getId());
        }
        Customer customer = customerService.findById(reservation.getCustomer().getId());
        if(customer == null){
            throw new CustomerNotFoundException(reservation.getCustomer().getId());
        }
        if(reservation.getNumeroPersonas() > touristPackage.getCupoMaximoPersonas()){
            throw new BusinessException("El numero de personas supera el cupo maximo disponible en el paquete");
        }
        reservation.setFechaReserva(LocalDate.now());
        reservation.setId(null);
        ReservationEntity savedReservation = reservationRepository.save(reservationMapper.domainToEntity(reservation));
        Reservation savedReservationDomain = reservationMapper.entityToDomain(savedReservation);
        savedReservationDomain.setCustomer(customer);
        savedReservationDomain.setTouristPackage(touristPackage);
        return savedReservationDomain;
    }

}
