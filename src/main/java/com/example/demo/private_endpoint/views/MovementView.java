package com.example.demo.private_endpoint.views;

import com.example.demo.private_endpoint.views.optinos.MovementCageSelection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovementView {
    // datos de usuario
    private String user_name;

    // datos del corral
    private String cage_code;
    private String cage_name;

    // datos del movimiento
    private String movement_type;
    private MovementCageSelection animalOrAnimalFood;
    private float amount;
    private float age;
    private float weigth;
    private String time;
}
