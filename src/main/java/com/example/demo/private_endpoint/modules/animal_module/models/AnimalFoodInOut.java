package com.example.demo.private_endpoint.modules.animal_module.models;

import com.example.demo.private_endpoint.modules.animal_module.models.options.AnimalFoodInOutOptions;
import com.example.demo.private_endpoint.modules.companymodule.Models.UserAsigned;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Movimientos_de_concentrado")
public class AnimalFoodInOut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimiento_de_concentrado")
    private long id;

    @Column(nullable = false, name = "tipo_de_movimiento")
    @NotNull(message = "el tipo de movimiento no puede ser null")
    private AnimalFoodInOutOptions type;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    @NotNull(message = "el usuario encargado no es valido")
    private UserAsigned user;

    @Column(nullable = false, name = "cantidad")
    @NotNull(message = "la cantidad no puede ser nula")
    private float amount;

    @Column(nullable = false, name = "fecha_movimiento")
    private LocalDateTime dateTime;
}
