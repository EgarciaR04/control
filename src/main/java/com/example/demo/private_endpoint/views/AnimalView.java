package com.example.demo.private_endpoint.views;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnimalView {
    private long id;
    private long id_user;
    private String animal_name;
    private String observations;
    private boolean hability;
}
