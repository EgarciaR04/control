package com.example.demo.private_endpoint.inputs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CageInput {
    private long user;
    private String code;
    private String name;
    private boolean active;
    private String observations;

    public boolean getActive() {
        return this.active;
    }
}
