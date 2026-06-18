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

public class ProductoVistaUsuarioDTO {

    private Long id;

    private String principioactivo;

    private String nombrecomercial;

    private BigDecimal precioigv;

    private String imagenurl;

    private Boolean estadoactivo;

}
