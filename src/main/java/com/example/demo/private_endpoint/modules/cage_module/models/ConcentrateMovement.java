package com.example.demo.private_endpoint.modules.cage_module.models;

import com.example.demo.private_endpoint.modules.cage_module.models.options.ConcentrateMovementOptions;
import com.example.demo.private_endpoint.modules.companymodule.Models.UserAsigned;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private ConcentrateMovementOptions moves;

    @Column(name = "concentrado_inicial", nullable = false)
    @NotBlank(message = "campo requerido")
    private float amount_before;

    @Column(name = "concentrado_despues", nullable = false)
    @NotBlank(message = "campo requerido")
    private float amount_after;

    @Column(name = "concentrado_movido")
    @NotBlank(message = "concentrado movido es requerido")
    @Min(0)
    private int amount;

    // usuario que realizó el movimiento
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserAsigned user;

}
