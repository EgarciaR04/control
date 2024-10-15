package com.example.demo.private_endpoint.modules.animal_module.models;

import com.example.demo.private_endpoint.modules.companymodule.Models.UserAsigned;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "concentrados")
public class Concentrate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private UserAsigned user;

    @NotNull(message = "Nombre de concentrado invalido")
    private String concentrate_name;

    @Column(nullable = true)
    @Size(max = 100)
    private String observations;

    @NotNull(message = "estado invalido")
    private boolean hablity;

    public boolean getHability() {
        return  this.hablity;
    }
}
