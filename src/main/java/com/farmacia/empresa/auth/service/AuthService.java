package com.farmacia.empresa.auth.service;

import com.farmacia.empresa.auth.dto.AuthResponse;
import com.farmacia.empresa.auth.dto.LoginRequest;
import com.farmacia.empresa.auth.dto.RegisterRequest;
import com.farmacia.empresa.auth.entity.UserWb;
import com.farmacia.empresa.auth.jwt.JwtTokenProvider;
import com.farmacia.empresa.auth.repository.UserWbRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserWbRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserWbRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtTokenProvider jwtTokenProvider,
                       AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }

        UserWb user = new UserWb();
        user.setNombre(request.getNombre());
        user.setApellido(request.getApellido());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        String rol = (request.getRol() == null || request.getRol().isBlank())
                ? "USER" : request.getRol().toUpperCase();
        user.setRol(rol);

        userRepository.save(user);

        String token = jwtTokenProvider.generarToken(
                user.getEmail(), user.getNombre(), user.getRol());

        return AuthResponse.builder()
                .token(token)
                .email(user.getEmail())
                .nombre(user.getNombre())
                .apellido(user.getApellido())
                .rol(user.getRol())
                .build();
    }

    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()));

        UserWb user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String token = jwtTokenProvider.generarToken(
                user.getEmail(), user.getNombre(), user.getRol());

        return AuthResponse.builder()
                .token(token)
                .email(user.getEmail())
                .nombre(user.getNombre())
                .apellido(user.getApellido())
                .rol(user.getRol())
                .build();
    }

}
