package com.example.demo.private_endpoint.modules.cage_module.models;

// import java.time.LocalDateTime;

import com.example.demo.private_endpoint.modules.companymodule.Models.UserAsigned;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "corral", uniqueConstraints = { @UniqueConstraint(columnNames = { "code" }) })
public class Cage {
    @Id
    @GeneratedValue
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

    private String code;
    private String name;
    private boolean active;
    @Column(nullable = true)
    private String observations;
}
