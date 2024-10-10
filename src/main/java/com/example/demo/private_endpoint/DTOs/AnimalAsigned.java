package com.example.demo.private_endpoint.DTOs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnimalAsigned {
    private long animalId;
    private String animalName;
    private float animalAmount;
}
