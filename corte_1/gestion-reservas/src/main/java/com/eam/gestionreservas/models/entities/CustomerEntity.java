package com.eam.gestionreservas.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
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
@SequenceGenerator(name = "SEQ_CLIENTE", sequenceName = "SEQ_CLIENTE", initialValue = 1, allocationSize = 1)
@Entity
@Table(name = "cliente", indexes = @Index(name = "UNIQUE_CEDULA", columnList = "cedula", unique = true))
public class CustomerEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CLIENTE")
        @Column(name = "id")
        private Integer id;

        @Column(name = "nombre", nullable = false, length = 100)
        private String nombre;

        @Column(name = "cedula", nullable = false, length = 12)
        private String cedula;

        @Column(name = "direccion", nullable = false, length = 100)
        private String direccion;

        @Column(name = "correo", nullable = false, length = 80)
        private String correo;

        @Column(name = "telefono", nullable = false, length = 12)
        private String telefono;



}
