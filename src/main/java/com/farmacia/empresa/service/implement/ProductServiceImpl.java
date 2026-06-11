package com.farmacia.empresa.service.implement;

import com.farmacia.empresa.dto.ProductDTO;
import com.farmacia.empresa.entity.Product;
import com.farmacia.empresa.repository.ProductRepository;
import com.farmacia.empresa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDTO> getProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(product -> new ProductDTO(
                product.getId(),
                product.getPrincipioactivo(),
                product.getNombrecomercial(),
                product.getPrecioigv(),
                product.getImagenurl()
        )).collect(Collectors.toList());
    }

    @Override
    public Optional<ProductDTO> getProduct(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);

        return productOptional.map(product -> new ProductDTO(
                product.getId(),
                product.getPrincipioactivo(),
                product.getNombrecomercial(),
                product.getPrecioigv(),
                product.getImagenurl()
        ));
    }


    @Override
    public void save(Product product) {
        product.setEstaactivo(true);
        productRepository.save(product);
    }

    @Override
    public Optional<Product> editarProduct(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void updateProduct(Product product) {
        if (productRepository.existsById(product.getId())) {
            productRepository.save(product);
        } else {
            throw new RuntimeException("No se puede actualizar, El producto no existe.");
        }
    }

    @Override
    public void desactivateProduct(Long id) {
        productRepository.findById(id).ifPresent(product -> {
            product.setEstaactivo(false);
            productRepository.save(product);
        });
    }

    @Override
    public void deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        }
    }
}
