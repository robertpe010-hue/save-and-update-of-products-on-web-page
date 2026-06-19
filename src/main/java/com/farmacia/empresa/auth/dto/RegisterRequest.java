package com.farmacia.empresa.auth.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String rol;

}
