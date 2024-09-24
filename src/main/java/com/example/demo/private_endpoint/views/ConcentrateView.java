package com.example.demo.private_endpoint.views;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConcentrateView {
    private long id;
    private String concentrate_name;
    private boolean hability;
    private String observations;
}
