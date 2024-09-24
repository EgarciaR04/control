package com.example.demo.private_endpoint.DTOs;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DTOConcentrate {
    @NotNull
    private String concentrate_name;
    private String observations;
    @NotNull
    private boolean hability;
}
