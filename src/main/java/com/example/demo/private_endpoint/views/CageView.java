package com.example.demo.private_endpoint.views;

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
    private String animal_name;
    private String code;
    private String name;
    private boolean active;
    private String observations;
    private float feedconcentrate;
    private Integer feedanimal;

}
