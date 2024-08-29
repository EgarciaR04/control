package com.example.demo.private_endpoint.modules.animal_module.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ingreso_salida_animales")
public class AnimalInOut {
    @Id
    @GeneratedValue
    private long id;

    private LocalDateTime movement_date;

    private float weight;

    @Column(nullable = true)
    private float age;

}
