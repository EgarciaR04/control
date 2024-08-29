package com.example.demo.private_endpoint.modules.companymodule.Service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.User.UserRepository;
import com.example.demo.User.User_;

import lombok.RequiredArgsConstructor;

// import lombok.AllArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<User_> getUserById(long id) {
        return userRepository.findById(id);
    }

    public User_ updateUserById(User_ request, long id) {
        User_ user = userRepository.findById(id).get();
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUsername(request.getUsername());
        user.setCountry(request.getCountry());
        user.setRole(request.getRole());

        userRepository.save(user);

        return user;
    }
}
