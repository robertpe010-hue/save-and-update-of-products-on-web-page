package com.farmacia.empresa.controller;

import com.farmacia.empresa.dto.ProductoCreacionDTO;
import com.farmacia.empresa.dto.ProductoVistaAdministradorDTO;
import com.farmacia.empresa.dto.ProductoVistaUsuarioDTO;
import com.farmacia.empresa.entity.Categoria;
import com.farmacia.empresa.entity.Laboratorio;
import com.farmacia.empresa.entity.Producto;
import com.farmacia.empresa.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/productos")

public class ProductController {
    @Autowired
    private ProductoService productoService;

    @GetMapping("/tienda")
    public ResponseEntity<List<ProductoVistaUsuarioDTO>> listarParaTienda() {
        List<ProductoVistaUsuarioDTO> productos = productoService.listarProductoParaTienda();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/admin")
    public ResponseEntity<List<ProductoVistaAdministradorDTO>> listarParaAdmin() {
        List<ProductoVistaAdministradorDTO> productos = productoService.listarProductoParaAdmin();
        return ResponseEntity.ok(productos);
    }

    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody ProductoCreacionDTO dto) {
        Producto nuevo = productoService.crearProducto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(
            @PathVariable Long id,
            @RequestBody ProductoCreacionDTO dto) {
        Producto actualizado = productoService.actualizarProducto(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<Void> desactivarProducto(@PathVariable Long id) {
        productoService.desactivarProducto(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/activar")
    public ResponseEntity<Void> activarProducto(@PathVariable Long id) {
        productoService.activarProducto(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/laboratorios")
    public ResponseEntity<List<Laboratorio>> listarLaboratorios() {
        return ResponseEntity.ok(productoService.listarLaboratoriosActivos());
    }

    @GetMapping("/categorias")
    public ResponseEntity<List<Categoria>> listarCategorias() {
        return ResponseEntity.ok(productoService.listarCategoriasActivas());
    }

}
