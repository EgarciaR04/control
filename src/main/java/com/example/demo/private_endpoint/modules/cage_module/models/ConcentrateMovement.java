package com.example.demo.private_endpoint.modules.cage_module.models;

import com.example.demo.private_endpoint.modules.cage_module.models.options.ConcentrateMovementOptions;
import com.example.demo.private_endpoint.modules.companymodule.Models.UserAsigned;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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
@Table(name = "movimiento_de_concentrado")
public class ConcentrateMovement {
    @Id
    @GeneratedValue
    private long id;

    // usuario que realizó el movimiento
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserAsigned user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_target_cage")
    private Cage target_cage;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_send_cage")
    private Cage send_cage;

    private ConcentrateMovementOptions moves;
    private int amount;
}
