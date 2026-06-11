package com.farmacia.empresa.service;

import com.farmacia.empresa.dto.ProductDTO;
import com.farmacia.empresa.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductDTO> getProducts();
    Optional<ProductDTO> getProduct(Long id);

    void save(Product product);
    Optional<Product> editarProduct(Long id);
    void updateProduct(Product product);
    void desactivateProduct(Long id);
    void deleteProduct(Long id);

}
