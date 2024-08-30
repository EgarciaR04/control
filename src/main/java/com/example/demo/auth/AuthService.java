package com.example.demo.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Jwt.JWTService;
import com.example.demo.User.Role;
import com.example.demo.User.UserRepository;
import com.example.demo.User.User_;
import com.example.demo.private_endpoint.modules.companymodule.Repositories.AsignedRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

        private final UserRepository userRepository;
        private final JWTService jwtService;
        private final PasswordEncoder passwordEncoder;
        private final AuthenticationManager authenticationManager;
        private final AsignedRepository asR;

        public AuthResponse login(LoginRequest request) {
                // TODO Auto-generated method stub
                authenticationManager
                                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),
                                                request.getPassword()));
                UserDetails userDetails = userRepository.findByUsername(request.getUsername()).orElseThrow();
                String token = jwtService.getToken(userDetails);
                try {
                        long id = userRepository.findByUsername2(request.getUsername());
                        long company = asR.findCompanyByUser(id);
                        long asig = asR.findIdByUserName(request.getUsername());
                        return AuthResponse.builder()
                                        .token(token)
                                        .user(id)
                                        .company(company)
                                        .asig(asig)
                                        .build();
                } catch (Exception ex) {
                        System.out.println(ex.getCause());
                        return AuthResponse.builder()
                                        .token(token)
                                        .company(-1)
                                        .user(-1)
                                        .asig(-1)
                                        .build();
                }
        }

        public AuthResponse register(RegisterRequest request) {
                // TODO Auto-generated method stub
                User_ user_ = User_.builder()
                                .username(request.getUsername())
                                .password(passwordEncoder.encode(request.getPassword()))
                                .firstname(request.getFirstname())
                                .lastname(request.getLastname())
                                .country(request.getCountry())
                                .role(Role.USER)
                                .build();
                userRepository.save(user_);

                return AuthResponse.builder()
                                .token(jwtService.getToken(user_))
                                .build();
        }

        public User_ resgiterUserByUser(User_ u) {
                User_ user = User_.builder()
                                .username(u.getUsername())
                                .password(passwordEncoder.encode(u.getPassword()))
                                .firstname(u.getFirstname())
                                .lastname(u.getLastname())
                                .country(u.getCountry())
                                .role(Role.USER)
                                .build();

                userRepository.save(user);

                return user;
        }

}
