package com.example.demo.private_endpoint.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PutCage {
    private String code;
    private String name;
    private boolean active;
    private String observations;

    public boolean getActive() {
        return this.active;
    }
}
