package com.example.demo.private_endpoint.modules.cage_module.models;

import com.example.demo.private_endpoint.modules.animal_module.models.AnimalInOut;
import com.example.demo.private_endpoint.modules.companymodule.Models.UserAsigned;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "asig_movimientos_corral", uniqueConstraints = { @UniqueConstraint(columnNames = { "animal_movement" }) })
public class AsigCageMovement {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private UserAsigned user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cage")
    private Cage cage;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_animal_movement")
    private AnimalInOut animal_movement;

}
