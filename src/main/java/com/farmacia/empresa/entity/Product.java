package com.farmacia.empresa.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "producto_catalogo")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sku", nullable = false, unique = true)
    private String sku;

    @Column(name = "codigo_barras", nullable = false)
    private String codigobarras;

    @Column(name = "principio_activo", nullable = false)
    private String principioactivo;

    @Column(name = "nombre_comercial", nullable = false)
    private String nombrecomercial;

    @Column(name = "laboratorio", nullable = false)
    private String laboratorio;

    @Column(name = "registro_sanitario", nullable = false)
    private String registrosanitario;

    @Column(name = "lote_actual", nullable = false)
    private String loteactual;

    @Column(name = "fecha_vencimiento", nullable = false)
    private String fechavencimiento;

    @Column(name = "stock", nullable = false)
    private String stock;

    @Column(name = "precio_igv", nullable = false)
    private String precioigv;

    @Column(name = "categoria_web", nullable = false)
    private String categoriaweb;

    @Column(name = "subcategoria_web", nullable = false)
    private String subcategoriaweb;

    @Column(name = "imagen_url", nullable = false)
    private String imageurl;

    @Column(name = "esta_activo", nullable = false)
    private String estaactivo;


}

