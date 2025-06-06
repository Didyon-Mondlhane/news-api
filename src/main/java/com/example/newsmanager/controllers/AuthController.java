package com.example.newsmanager.controllers;

import com.example.newsmanager.domain.auth.*;
import com.example.newsmanager.security.JwtService;
import com.example.newsmanager.security.UserDetailsImpl;
import com.example.newsmanager.services.UserService;
import jakarta.validation.Valid;
import javax.naming.AuthenticationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager,
                        UserService userService,
                        JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) throws AuthenticationException {
        var authentication = new UsernamePasswordAuthenticationToken(
                loginRequest.email(), 
                loginRequest.password());
        
        var auth = authenticationManager.authenticate(authentication);
        var user = (UserDetailsImpl) auth.getPrincipal();
        var token = jwtService.generateToken(user.getUser());

        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterRequest registerRequest) {
        userService.registerUser(registerRequest);
        return ResponseEntity.ok().build();
    }
}