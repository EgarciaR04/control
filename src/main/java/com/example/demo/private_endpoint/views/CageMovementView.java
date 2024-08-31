package com.example.demo.private_endpoint.views;

import java.time.LocalDateTime;

import com.example.demo.private_endpoint.modules.animal_module.models.options.AnimalInOutOptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CageMovementView {
    private String user_username;
    private String cage_code;
    private AnimalInOutOptions movement_option;
    private LocalDateTime time;

}
