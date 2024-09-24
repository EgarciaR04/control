package com.example.demo.private_endpoint.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AsignCageAnimalData {
    private long animal;
    private Integer animal_amount;
}
