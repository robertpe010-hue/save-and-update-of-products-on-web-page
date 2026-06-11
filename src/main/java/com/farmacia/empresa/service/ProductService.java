package com.farmacia.empresa.service;

import com.farmacia.empresa.dto.ProductDTO;
import com.farmacia.empresa.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductDTO> getProducts();
    Optional<ProductDTO> getProduct(Long id);

    void crearProduct(Product product);
    void editarProducto(Product product);
    void desactivarProducto(Long id);
    void borrarProducto(Long id);
}
