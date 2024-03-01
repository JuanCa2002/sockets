package com.eam.gestionreservas.services;

import com.eam.gestionreservas.exceptions.IntegritySqlException;
import com.eam.gestionreservas.models.domains.Destination;
import com.eam.gestionreservas.models.domains.TouristPackage;
import com.eam.gestionreservas.models.domains.TouristPackageDestination;
import com.eam.gestionreservas.models.entities.TouristPackageEntity;
import com.eam.gestionreservas.repositories.TouristPackageRepository;
import com.eam.gestionreservas.services.mappers.TouristPackageMapper;
import com.eam.gestionreservas.utils.GenerateExceptionUtils;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TouristPackageService {

    private final TouristPackageRepository touristPackageRepository;

    private final TouristPackageMapper touristPackageMapper;

    private final TouristPackageDestinationService touristPackageDestinationService;

    private final DestinationService destinationService;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = IntegritySqlException.class)
    public TouristPackage save(TouristPackage touristPackage) throws IntegritySqlException {
        try{
            touristPackage.setId(null);
            TouristPackageEntity savedTouristPackage = touristPackageRepository.saveAndFlush(touristPackageMapper.domainToEntity(touristPackage));
            TouristPackage savedTouristPackageDomain = touristPackageMapper.entityToDomain(savedTouristPackage);
            touristPackageDestinationService.saveAll(getTouristPackageDestinations(touristPackage.getDestinationList(), savedTouristPackageDomain));
            List<Destination> destinations = destinationService.findDestinationListByIds(getDestinationsIds(touristPackage.getDestinationList()));
            savedTouristPackageDomain.setDestinationList(destinations);
            return savedTouristPackageDomain;
        }catch (DataIntegrityViolationException e){
            String constraintName = ((ConstraintViolationException)e.getCause()).getConstraintName();
            throw GenerateExceptionUtils.generateExceptionUtil(constraintName!=null?constraintName:"");
        }
    }

    @Transactional(readOnly = true)
    public TouristPackage findById(Integer id){
        TouristPackage touristPackage = touristPackageMapper.entityToDomain(touristPackageRepository.findById(id).orElse(null));
        if(touristPackage == null){
            return null;
        }
        List<TouristPackageDestination> touristPackageDestinations = touristPackageDestinationService.findByTouristPackageId(touristPackage.getId());
        touristPackage.setDestinationList(getDestinationsByTouristPackage(touristPackageDestinations));
        return touristPackage;
    }

    @Transactional(readOnly = true)
    public List<TouristPackage> findAll(){
        List<TouristPackage> touristPackages = touristPackageMapper.entitiesToDomains(touristPackageRepository.findAll());
        List<TouristPackage> resultList = new ArrayList<>(touristPackages.size());
        for(TouristPackage touristPackage : touristPackages){
            List<TouristPackageDestination> touristPackageDestinations = touristPackageDestinationService.findByTouristPackageId(touristPackage.getId());
            touristPackage.setDestinationList(getDestinationsByTouristPackage(touristPackageDestinations));
            resultList.add(touristPackage);
        }
        return resultList;
    }

    public List<Destination> getDestinationsByTouristPackage(List<TouristPackageDestination> touristPackageDestinations){
        List<Destination> destinations = new ArrayList<>(touristPackageDestinations.size());
        for(TouristPackageDestination touristPackageDestination: touristPackageDestinations){
            destinations.add(touristPackageDestination.getDestination());
        }
        return destinations;
    }


    public List<TouristPackageDestination> getTouristPackageDestinations(List<Destination> destinations, TouristPackage touristPackage){
        List<TouristPackageDestination> touristPackageDestinations = new ArrayList<>(destinations.size());
        for(Destination destination : destinations){
            TouristPackageDestination touristPackageDestination = new TouristPackageDestination();
            touristPackageDestination.setId(null);
            touristPackageDestination.setDestination(destination);
            touristPackageDestination.setTouristPackage(touristPackage);
            touristPackageDestinations.add(touristPackageDestination);
        }
        return touristPackageDestinations;
    }

    public List<Integer> getDestinationsIds(List<Destination> destinations){
        List<Integer> destinationsIds = new ArrayList<>(destinations.size());
        for(Destination destination : destinations){
            destinationsIds.add(destination.getId());
        }
        return destinationsIds;
    }


}
