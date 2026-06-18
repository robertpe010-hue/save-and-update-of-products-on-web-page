package com.farmacia.empresa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProductoCreacionDTO {

    @NotBlank(message = "El SKU es obligatorio")
    private String sku;

    private String codigodebarras;

    @NotBlank(message = "El nombre comercial es obligatorio")
    private String nombrecomercial;

    @NotBlank(message = "El principio activo es obligatorio")
    private String principioactivo;

    @NotBlank(message = "El registro sanitario es obligatorio")
    private String registrosanitario;

    @NotNull(message = "Debe seleccionar un laboratorio")
    private Long laboratorioid;

    @NotNull(message = "Debe seleccionar una categoría")
    private Long categoriaid;

    @NotBlank(message = "La imagen es obligatoria")
    private String imagenurl;

    @NotNull(message = "El precio es obligatorio")
    private BigDecimal precioigv;

    private Boolean estaactivo;

}
