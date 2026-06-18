package com.farmacia.empresa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProductoVistaAdministradorDTO {
    private Long id;

    private String sku;

    private String nombrecomercial;

    private String laboratorio;

    private String categoria;

    private BigDecimal precioigv;

    private Boolean estaadoactivo;
}
