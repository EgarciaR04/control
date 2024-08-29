package com.example.demo.private_endpoint.modules.cage_module.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cantidad_de_concentrado")
public class FeedConcentrate {
    @Id
    @GeneratedValue
    private long id;

    private int amount;

}
