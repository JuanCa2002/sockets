package com.eam.gestionreservas.models.entities;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@SequenceGenerator(name = "SEQ_PAQUETE_TURISTICO", sequenceName = "SEQ_PAQUETE_TURISTICO", initialValue = 1, allocationSize = 1)
@Entity
@Table(name = "paquete_turistico" , indexes = @Index(name = "UNIQUE_NOMBRE", columnList = "nombre", unique = true))
public class TouristPackageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PAQUETE_TURISTICO")
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "duracion", nullable = false, length = 3)
    private Integer duracion;

    @Column(name = "cupo_maximo_personas", nullable = false, length = 3)
    private Integer cupoMaximoPersonas;

    @Column(name = "precio", nullable = false, length = 12)
    private Long precio;

    @OneToMany(mappedBy = "touristPackage", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<TouristPackageDestinationEntity> touristPackageDestinationEntities;

}
