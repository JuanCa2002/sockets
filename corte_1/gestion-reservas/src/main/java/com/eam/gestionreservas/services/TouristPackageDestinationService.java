package com.eam.gestionreservas.services;

import com.eam.gestionreservas.exceptions.IntegritySqlException;
import com.eam.gestionreservas.models.domains.TouristPackageDestination;
import com.eam.gestionreservas.models.entities.TouristPackageDestinationEntity;
import com.eam.gestionreservas.repositories.TouristPackageDestinationRepository;
import com.eam.gestionreservas.services.mappers.DestinationMapper;
import com.eam.gestionreservas.services.mappers.TouristPackageDestinationMapper;
import com.eam.gestionreservas.services.mappers.TouristPackageMapper;
import com.eam.gestionreservas.utils.GenerateExceptionUtils;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TouristPackageDestinationService {

    private final TouristPackageDestinationRepository touristPackageDestinationRepository;

    private final TouristPackageMapper touristPackageMapper = new TouristPackageMapper();

    private final DestinationMapper destinationMapper = new DestinationMapper();
    private final TouristPackageDestinationMapper touristPackageDestinationMapper = new TouristPackageDestinationMapper(touristPackageMapper, destinationMapper);

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = IntegritySqlException.class)
    public List<TouristPackageDestination> saveAll(List<TouristPackageDestination> touristPackageDestinations) throws IntegritySqlException {
        try{
            List<TouristPackageDestinationEntity> savedTouristPackageDestinations = touristPackageDestinationRepository.saveAllAndFlush(touristPackageDestinationMapper.domainsToEntities(touristPackageDestinations));
            return touristPackageDestinationMapper.entitiesToDomains(savedTouristPackageDestinations);
        }catch (DataIntegrityViolationException e){
            if(((ConstraintViolationException) e.getCause()).getSQLException() != null){
                Integer code = ((ConstraintViolationException)e.getCause()).getSQLException().getErrorCode();
                String codeString = code.toString();
                throw GenerateExceptionUtils.generateExceptionUtil(codeString);
            }
            String constraintName = ((ConstraintViolationException)e.getCause()).getConstraintName();
            throw GenerateExceptionUtils.generateExceptionUtil(constraintName);
        }
    }
    public List<TouristPackageDestination> findByTouristPackageId(Integer touristPackageId){
        return touristPackageDestinationMapper.entitiesToDomains(touristPackageDestinationRepository.findByTouristPackageId(touristPackageId));
    }
}
