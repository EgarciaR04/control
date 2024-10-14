package com.example.demo.private_endpoint.modules.animal_module.models;

import com.example.demo.private_endpoint.modules.animal_module.models.options.AnimalInOutOptions;
import com.example.demo.private_endpoint.modules.cage_module.models.Cage;
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
@Table(name = "Traslados_de_comida")
public class TrasladateAnimalFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_traslado_de_concentrado")
    private long id;

    @Column(nullable = false, name = "tipo_de_movimiento")
    @NotNull(message = "El tipo de movimiento no puede ser nulo")
    @NotBlank(message = "El tipo de movimiento no puede estar en blanco")
    private AnimalInOutOptions type;

    @Column(nullable = false, name = "fecha_del_traslado")
    @NotNull(message = "La fecha no puede ser nula")
    private LocalDateTime date;

    @Column(nullable = false, name = "cantidad_del_traslado")
    @NotNull(message = "La cantidad de concentrado a trasladar no puede ser nula")
    @NotBlank(message = "La cantidad no puede estar en blanco")
    private float amount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private UserAsigned user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_target_cage")
    private Cage targetCage;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_origin_cage")
    private Cage originCage;
}