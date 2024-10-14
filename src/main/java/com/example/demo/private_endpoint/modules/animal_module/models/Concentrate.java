package com.example.demo.private_endpoint.modules.animal_module.models;

import com.example.demo.private_endpoint.modules.companymodule.Models.UserAsigned;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    // send by url
    private UserAsigned user;

    @NotNull
    private String concentrate_name;
    @Column(nullable = true)
    private String observations;
    private boolean hablity;

    public boolean getHability() {
        return  this.hablity;
    }
}
