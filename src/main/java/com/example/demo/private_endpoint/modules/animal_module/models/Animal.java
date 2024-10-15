package com.example.demo.private_endpoint.modules.animal_module.models;

import com.example.demo.private_endpoint.modules.companymodule.Models.UserAsigned;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
@Table(name = "animales", uniqueConstraints = { @UniqueConstraint(columnNames = { "code" }) })
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // usuario creador
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private UserAsigned user;

    @Column(nullable = false)
    @NotNull(message = "nombre de animal no valido")
    private String animal_name;

    @Column(nullable = true)
    @Size(max = 100)
    private String observations;

    @Column(nullable = false)
    @NotNull(message = "valor de hablitado invalido")
    private boolean hability;

    public boolean getHability() {
        return this.hability;
    }

}
