package com.example.demo;

import com.example.demo.User.UserRepository;
import com.example.demo.User.User_;
import com.example.demo.auth.AuthService;
import com.example.demo.auth.RegisterRequest;
import com.example.demo.private_endpoint.modules.companymodule.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userService;
    private final AuthService authService;

    @Override
    public void run(String... args) throws Exception {
        // Verificamos si existe algún usuario en la base de datos
        if (userService.findAll().isEmpty()) {
            // Si no hay usuarios, creamos el usuario inicial
            RegisterRequest admin = new RegisterRequest();
            admin.setUsername("admin@inicial");
            admin.setFirstname("Admin");
            admin.setLastname("Admin");
            admin.setPassword("Admin.2024");
            admin.setHability(true);

            authService.register(admin);

            System.out.println("Usuario administrador creado exitosamente.");
        } else {
            System.out.println("Ya existen usuarios en la base de datos. No se creó un nuevo usuario administrador.");
        }
    }
}
