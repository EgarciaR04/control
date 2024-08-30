package com.example.demo.private_endpoint.views;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnimalInOutView {
    private long user;
    private LocalDateTime date;
    private float weight;
    private float age;
}
