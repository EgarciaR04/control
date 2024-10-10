package com.example.demo.private_endpoint.DTOs;

import jakarta.validation.constraints.Null;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConcentrateAsigned {
    @Null
    private long concentrateId;
    private String concentrateName;
    private float concentrateAmount;
}
