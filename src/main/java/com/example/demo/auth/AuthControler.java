package com.example.demo.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("auth/")
@RequiredArgsConstructor
public class AuthControler {

    private final AuthService authservice;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        // ResponseEntity representa toda la respuesta http, como encabezados, cuerpos y todo sobre el request
        return ResponseEntity.ok(authservice.login(request));
    }

    
    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> resgister(@RequestBody RegisterRequest request){
        // ResponseEntity representa toda la respuesta http, como encabezados, cuerpos y todo sobre el request
        return ResponseEntity.ok(authservice.register(request));
    }
}
