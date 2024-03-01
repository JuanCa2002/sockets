package com.eam.gestionreservas.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
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

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@SequenceGenerator(name = "SEQ_RESERVACION", sequenceName = "SEQ_RESERVACION", initialValue = 1, allocationSize = 1)
@Entity
@Table(name = "reservacion")
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RESERVACION")
    @Column(name = "id")
    private Integer id;

    @Column(name="numero_personas", nullable = false, length = 3)
    private Integer numeroPersonas;

    @Column(name = "fecha_reserva", nullable = false)
    private LocalDate fechaReserva;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paquete_turistico_id", nullable = false, foreignKey = @ForeignKey(name = "FK_RESERVACION_PAQUETE_TURISTICO"))
    private TouristPackageEntity touristPackage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false, foreignKey = @ForeignKey(name = "FK_CLIENTE"))
    private CustomerEntity customer;
}
