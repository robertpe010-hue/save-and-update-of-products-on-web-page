package com.farmacia.empresa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProductDTO {
    private Long id;
    private String principioactivo;
    private String nombrecomercial;
    private String precio_igv;
    private String imagenurl;
}
