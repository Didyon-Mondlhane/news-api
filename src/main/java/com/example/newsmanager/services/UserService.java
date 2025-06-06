package com.example.newsmanager.services;

import com.example.newsmanager.domain.auth.User;
import com.example.newsmanager.domain.auth.UserRole;
import com.example.newsmanager.domain.auth.RegisterRequest;
import com.example.newsmanager.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.email())) {
            throw new RuntimeException("O email já está em uso!");
        }

        if (userRepository.existsByUsername(registerRequest.username())) {
            throw new RuntimeException("O email já está em uso!");
        }

        User newUser = new User();
        newUser.setUsername(registerRequest.username());
        newUser.setEmail(registerRequest.email());
        newUser.setPassword(passwordEncoder.encode(registerRequest.password()));
        newUser.setRole(UserRole.READER);

        return userRepository.save(newUser);
    }


}