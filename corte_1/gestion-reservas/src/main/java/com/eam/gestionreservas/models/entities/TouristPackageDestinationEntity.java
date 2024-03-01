package com.eam.gestionreservas.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@SequenceGenerator(name = "SEQ_PAQUETE_DESTINO", sequenceName = "SEQ_PAQUETE_DESTINO", initialValue = 1, allocationSize = 1)
@Entity
@Table(name = "paquete_turistico_destino")
public class TouristPackageDestinationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PAQUETE_DESTINO")
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paquete_turistico_id", nullable = false, foreignKey = @ForeignKey(name = "FK_PAQUETE_TURISTICO"))
    private TouristPackageEntity touristPackage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destino_id", nullable = false, foreignKey = @ForeignKey(name = "FK_DESTINO"))
    private DestinationEntity destination;


}
