package com.example.demo.private_endpoint.modules.companymodule.Service;

import java.util.Optional;

import com.example.demo.private_endpoint.DTOs.ActUser;
import com.example.demo.private_endpoint.DTOs.ActUserPassword;
import com.example.demo.private_endpoint.modules.companymodule.Repositories.AsignedRepository;
import com.example.demo.private_endpoint.views.Message;
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

    private final AsignedRepository asignedRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserView getUserById(long id) {
        Optional<User_> user_op = asignedRepository.findUserByIdAsig(id);
        try {
            User_ user = user_op.get();

            UserView userv = new UserView();

            userv.setId(id);
            userv.setUsername(user.getUsername());
            userv.setFirstname(user.getFirstname());
            userv.setLastname(user.getLastname());
            userv.setRole(user.getRole());
            userv.setTel(user.getTel());
            userv.setHability(user.getHability());
            userv.setChangePassword(user.getChangePassword());
            userv.setChangePasswordNextSession(user.getChangePasswordNextSession());


            return userv;

        } catch (Exception ex) {
            UserView userv = new UserView();
            return userv;
        }
    }

    public Message updateUserById(ActUser request, long id) {
        User_ user = asignedRepository.findUserByIdAsig(id).get();
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setUsername(request.getUsername());
        user.setTel(request.getTel());
        user.setHability(request.getHability());
        user.setChangePassword(request.getChangePassword());
        user.setChangePasswordNextSession(request.getChangePasswordNextSession());

        userRepository.save(user);

        Message message = new Message("");
        message.setMessage("Usuario actualizado correctamente");

        return message;
    }

    public Message updatePassword(ActUserPassword request, long id){
        User_ user = asignedRepository.findUserByIdAsig(id).get();
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        Message message = new Message("");
        message.setMessage("Contraseña actualizada correctamente");

        return  message;
    }
}
