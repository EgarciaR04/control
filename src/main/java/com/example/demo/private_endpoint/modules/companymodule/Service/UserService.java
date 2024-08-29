package com.example.demo.private_endpoint.modules.companymodule.Service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.User.UserRepository;
import com.example.demo.User.User_;
import com.example.demo.private_endpoint.views.UserView;

import lombok.RequiredArgsConstructor;

// import lombok.AllArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserView getUserById(long id) {
        Optional<User_> user_op = userRepository.findById(id);
        try {
            User_ user = user_op.get();

            UserView userv = new UserView();

            userv.setId(user.getId());
            userv.setUsername(user.getUsername());
            userv.setFirstname(user.getFirstname());
            userv.setLastname(user.getLastname());
            userv.setCountry(user.getCountry());
            userv.setRole(user.getRole());
            userv.setTel(user.getTel());
            userv.setHability(user.getHability());

            return userv;

        } catch (Exception ex) {
            UserView userv = new UserView();
            return userv;
        }
    }

    public UserView updateUserById(User_ request, long id) {
        User_ user = userRepository.findById(id).get();
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUsername(request.getUsername());
        user.setCountry(request.getCountry());
        user.setTel(request.getTel());
        user.setRole(request.getRole());

        userRepository.save(user);

        // crear usuario para mostrar

        UserView userv = new UserView();
        userv.setId(user.getId());
        userv.setFirstname(request.getFirstname());
        userv.setLastname(request.getLastname());
        userv.setUsername(request.getUsername());
        userv.setCountry(request.getCountry());
        userv.setRole(request.getRole());
        userv.setTel(request.getTel());

        return userv;
    }
}
