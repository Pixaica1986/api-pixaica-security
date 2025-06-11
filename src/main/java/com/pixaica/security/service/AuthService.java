package com.pixaica.security.service;

import com.pixaica.security.dto.NewUserDto;
import com.pixaica.security.entities.User;
import com.pixaica.security.enums.RoleList;
import com.pixaica.security.repositories.RoleRepository;
import com.pixaica.security.repositories.UserRepository;
import com.pixaica.security.utils.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    public String authenticate(String username, String password) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(usernamePasswordAuthenticationToken);
        return jwtUtil.generateToken(authentication);
    }

    public void registerUser(NewUserDto newUserDto) {
        if (userRepository.existsByUsername(newUserDto.getUsername())) {
            throw new IllegalArgumentException("El nombre de usuario ya existe!");
        }
        var roleUser = roleRepository.findByName(RoleList.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        User user = new User(newUserDto.getUsername(), passwordEncoder.encode(newUserDto.getPassword()), roleUser);
        userRepository.save(user);
    }
}
