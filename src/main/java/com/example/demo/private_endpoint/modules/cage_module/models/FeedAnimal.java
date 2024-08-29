package com.example.demo.private_endpoint.modules.cage_module.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
@Table(name = "cantidad_de_animales", uniqueConstraints = { @UniqueConstraint(columnNames = { "cage" }) })
public class FeedAnimal {
    @Id
    @GeneratedValue
    private long id;

    private Integer animal_amount;

}
