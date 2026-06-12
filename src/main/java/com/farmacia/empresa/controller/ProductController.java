package com.farmacia.empresa.controller;

import com.farmacia.empresa.dto.ProductDTO;
import com.farmacia.empresa.entity.Product;
import com.farmacia.empresa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProducts() {
        List<ProductDTO> products = productService.getProducts();
        return ResponseEntity.ok(products);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id) {
        Optional<ProductDTO> product = productService.getProduct(id);
        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping("/admin/crear")
    public ResponseEntity<String> crearProducto(@RequestBody Product product) {
        try {
            productService.crearProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Producto creado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al crear el producto: " + e.getMessage());
        }
    }


    @PutMapping("/admin/{id}")
    public ResponseEntity<String> editarProducto(@PathVariable Long id, @RequestBody Product product) {
        try {
            // Validar que el ID del producto coincida con el de la URL
            product.setId(id);
            productService.editarProducto(product);
            return ResponseEntity.ok("Producto actualizado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al editar el producto: " + e.getMessage());
        }
    }


    @DeleteMapping("/admin/{id}")
    public ResponseEntity<String> borrarProducto(@PathVariable Long id) {
        try {
            productService.borrarProducto(id);
            return ResponseEntity.ok("Producto eliminado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al eliminar el producto: " + e.getMessage());
        }
    }


    @PatchMapping("/admin/{id}/desactivar")
    public ResponseEntity<String> desactivarProducto(@PathVariable Long id) {
        try {
            productService.desactivarProducto(id);
            return ResponseEntity.ok("Producto desactivado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al desactivar el producto: " + e.getMessage());
        }
    }

}
