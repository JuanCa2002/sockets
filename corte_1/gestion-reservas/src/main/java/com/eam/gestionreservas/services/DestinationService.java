package com.eam.gestionreservas.services;

import com.eam.gestionreservas.models.domains.Destination;
import com.eam.gestionreservas.models.entities.DestinationEntity;
import com.eam.gestionreservas.repositories.DestinationRepository;
import com.eam.gestionreservas.services.mappers.DestinationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DestinationService {

    private final DestinationRepository destinationRepository;

    private final DestinationMapper destinationMapper;
    @Transactional(readOnly = true)
    public List<Destination> findDestinationListByIds(List<Integer> ids){
        List<DestinationEntity> destinationEntities = destinationRepository.findAllById(ids);
        return destinationMapper.entitiesToDomains(destinationEntities);
    }
}
