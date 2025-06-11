package com.pixaica.security.controller;

import com.pixaica.security.dto.LoginUserDto;
import com.pixaica.security.dto.NewUserDto;
import com.pixaica.security.service.AuthService;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginUserDto loginUserDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body("Revisa sus credenciales");
        }
        try {
            String jwt = authService.authenticate(loginUserDto.getUsername(), loginUserDto.getPassword());
            return ResponseEntity.ok(jwt);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Revise sus credenciales");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody NewUserDto newUserDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Revisa sus credenciales");
        }
        try {
            authService.registerUser(newUserDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Registrado...");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/check-auth")
    public ResponseEntity<?> checkAuth() {
        return ResponseEntity.ok().body("Authenticado");
    }
}
