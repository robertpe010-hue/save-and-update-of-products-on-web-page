package com.farmacia.empresa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY) /// Anotacion de Uno a muchos: Una categoria presenta varias sub-categorias.
    @JoinColumn(name = "parent_id")
    private Categoria categoriaPadre;

    @Column(name= "esta_activo", nullable = false)
    private Boolean estaActivo = true;

    @OneToMany(mappedBy = "categoriaPadre", fetch = FetchType.LAZY) /// No implementa o llama una columna de la base de datos, no crea tabla ni nada fisico en SQL.
    private List<Categoria> subcategorias; /// Podemos llamar a las sub categorias de la categoria padre.
}
