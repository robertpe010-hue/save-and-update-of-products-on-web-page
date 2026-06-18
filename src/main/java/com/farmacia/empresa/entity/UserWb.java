package com.farmacia.empresa.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "users_web")
public class UserWb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "apellido", nullable = false)
    private String apellido;
    @Column(name = "DNI", nullable = false)
    private String dni;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "Ncelular", nullable = false)
    private String numerocelular;
    @Column(name = "firstpassword", nullable = false)
    private String passwordNcrpt;


}
