package com.example.demo.private_endpoint.modules.cage_module.models;

import com.example.demo.private_endpoint.modules.animal_module.models.AnimalFoodInOut;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "asignacion_movimientos_concentrado")
public class AsigAnimalFoodCageMovement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cage")
    private Cage cage;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_animalfood_movement")
    private AnimalFoodInOut animalfood_movement;
}
