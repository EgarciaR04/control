package com.example.demo.private_endpoint.modules.animal_module.models;

import java.time.LocalDateTime;

import com.example.demo.private_endpoint.modules.animal_module.models.options.AnimalInOutOptions;
import com.example.demo.private_endpoint.modules.companymodule.Models.UserAsigned;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private UserAsigned user;

    private LocalDateTime movement_date;

    @Column(nullable = true)
    private float weight;

    @Column(nullable = true)
    private float age;

    @Column(nullable = true)
    private float amount_animals;

    @Enumerated(EnumType.STRING)
    private AnimalInOutOptions type;

}
