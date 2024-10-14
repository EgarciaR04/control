package com.example.demo.private_endpoint.views;

import com.example.demo.private_endpoint.modules.animal_module.models.options.AnimalInOutOptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CageAnimalMovementView {
    // datos de usuario
    private String user_username;
    // datos de corral
    private String cage_code;
    private String cage_name;
    // datos de animal
    private String animal_type;
    private float weigth;
    private float age;
    // datos del movimiento
    private AnimalInOutOptions movement_option;
    private String time;

}
