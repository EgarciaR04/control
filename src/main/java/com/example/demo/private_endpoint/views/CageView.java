package com.example.demo.private_endpoint.views;

import com.example.demo.private_endpoint.DTOs.AnimalAsigned;
import com.example.demo.private_endpoint.DTOs.ConcentrateAsigned;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CageView {
    private long id;
    private long user;
    @Nullable
    private ConcentrateAsigned concentrateAsigned;
    @Nullable
    private AnimalAsigned animalAsigned;
    private String code;
    private String name;
    private boolean active;
    private String observations;

}
