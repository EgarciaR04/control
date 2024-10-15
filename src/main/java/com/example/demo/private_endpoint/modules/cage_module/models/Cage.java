package com.example.demo.private_endpoint.modules.cage_module.models;

import com.example.demo.private_endpoint.modules.animal_module.models.Animal;

// import java.time.LocalDateTime;

import com.example.demo.private_endpoint.modules.animal_module.models.Concentrate;
import com.example.demo.private_endpoint.modules.companymodule.Models.UserAsigned;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "corral")
public class Cage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // usuario creador
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private UserAsigned user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_fedd_c")
    private FeedConcentrate feedConcentrate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_feed_a")
    private FeedAnimal feedAnimal;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_animal", nullable = true)
    private Animal animal;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_concentrate")
    private Concentrate concentrate;

    @Column(nullable = false)
    @NotNull()
    @Size(max = 10)
    private String code;

    @Column(nullable = false)
    @NotNull
    @Size(max = 25)
    private String name;

    @Column(nullable = false)
    @NotNull
    private boolean active;

    @Column(nullable = true)
    @Size(max = 100)
    private String observations;

    public boolean getActive() {
        return this.active;
    }
}
