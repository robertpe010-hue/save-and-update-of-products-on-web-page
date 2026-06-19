package com.farmacia.empresa.auth.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {

    private String token;
    private String email;
    private String nombre;
    private String apellido;
    private String rol;

}
