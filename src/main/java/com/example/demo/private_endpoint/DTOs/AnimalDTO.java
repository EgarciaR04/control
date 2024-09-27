package com.example.demo.private_endpoint.DTOs;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AnimalDTO {
    @NotNull
    private String animal_name;
    private String observations;
    private boolean hability;
}
