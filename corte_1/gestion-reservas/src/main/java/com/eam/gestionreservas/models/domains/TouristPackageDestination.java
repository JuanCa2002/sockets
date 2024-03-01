package com.eam.gestionreservas.models.domains;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TouristPackageDestination {

    private Integer id;

    private TouristPackage touristPackage;

    private Destination destination;
}
