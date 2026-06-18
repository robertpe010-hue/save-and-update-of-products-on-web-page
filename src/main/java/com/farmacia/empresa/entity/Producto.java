package com.farmacia.empresa.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sku", unique = true, nullable = false)
    private String sku;

    @Column(name = "codigo_barras")
    private String codigodebarras;

    @Column(name = "nombre_comercial", nullable = false)
    private String nombrecomercial;

    @Column(name = "principio_activo",nullable = false)
    private String principioactivo;

    @Column(name = "registro_sanitario", nullable = false)
    private String registrosanitario;

    /// Relacion de la clase java: Categoria y Laboratorio
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "laboratorio_id", nullable = false)
    private Laboratorio laboratorio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;
    ///
    @Column(name = "imagen_url", nullable = false)
    private String imagenurl;

    @Column(name = "precio_igv", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioigv;

    @Column(name = "esta_activo", nullable = false)
    private Boolean estadoactivo = true;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductoLote> lotes;
}
