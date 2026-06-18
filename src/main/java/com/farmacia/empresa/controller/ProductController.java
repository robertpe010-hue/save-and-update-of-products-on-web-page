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

public class ProductController {
    @Autowired
    private ProductoService productoService;
    // =========================================================
    // 1. LISTAR PRODUCTOS PARA LA TIENDA (PÚBLICO)
    // Ruta: GET http://localhost:8080/api/productos/tienda
    // =========================================================
    @GetMapping("/tienda")
    public ResponseEntity<List<ProductoVistaUsuarioDTO>> listarParaTienda() {
        // Llamamos al servicio y devolvemos la lista con un código 200 (OK)
        List<ProductoVistaUsuarioDTO> productos = productoService.listarProductoParaTienda();
        return ResponseEntity.ok(productos);
    }
    // =========================================================
    // 2. LISTAR PRODUCTOS PARA EL ADMIN
    // Ruta: GET http://localhost:8080/api/productos/admin
    // =========================================================
    @GetMapping("/admin")
    public ResponseEntity<List<ProductoVistaAdministradorDTO>> listarParaAdmin() {
        List<ProductoVistaAdministradorDTO> productos = productoService.listarProductoParaAdmin();
        return ResponseEntity.ok(productos);
    }
    // =========================================================
    // 3. CREAR PRODUCTO
    // Ruta: POST http://localhost:8080/api/productos
    // El Frontend envía un JSON en el cuerpo de la petición
    // =========================================================
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody ProductoCreacionDTO dto) {
        // @RequestBody le dice a Spring: "El JSON que viene del Frontend, conviértelo a este DTO"
        Producto nuevo = productoService.crearProducto(dto);
        // Devolvemos código 201 (CREATED) en lugar de 200, porque creamos algo nuevo
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }
    // =========================================================
    // 4. ACTUALIZAR PRODUCTO
    // Ruta: PUT http://localhost:8080/api/productos/15
    // El "15" es el ID del producto a actualizar
    // =========================================================
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(
            @PathVariable Long id,  // @PathVariable captura el "15" de la URL
            @RequestBody ProductoCreacionDTO dto) {
        Producto actualizado = productoService.actualizarProducto(id, dto);
        return ResponseEntity.ok(actualizado);
    }
    // =========================================================
    // 5. DESACTIVAR PRODUCTO (Soft Delete)
    // Ruta: PATCH http://localhost:8080/api/productos/15/desactivar
    // =========================================================
    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<Void> desactivarProducto(@PathVariable Long id) {
        productoService.desactivarProducto(id);
        // Devolvemos 204 (NO CONTENT) porque la operación fue exitosa pero no hay datos que devolver
        return ResponseEntity.noContent().build();
    }
    // =========================================================
    // 6. ACTIVAR PRODUCTO
    // Ruta: PATCH http://localhost:8080/api/productos/15/activar
    // =========================================================
    @PatchMapping("/{id}/activar")
    public ResponseEntity<Void> activarProducto(@PathVariable Long id) {
        productoService.activarProducto(id);
        return ResponseEntity.noContent().build();
    }
    // =========================================================
    // 7. ELIMINAR PRODUCTO (Hard Delete)
    // Ruta: DELETE http://localhost:8080/api/productos/15
    // =========================================================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
    // =========================================================
    // 8. LISTAR LABORATORIOS ACTIVOS (Para el ComboBox)
    // Ruta: GET http://localhost:8080/api/productos/laboratorios
    // =========================================================
    @GetMapping("/laboratorios")
    public ResponseEntity<List<Laboratorio>> listarLaboratorios() {
        return ResponseEntity.ok(productoService.listarLaboratoriosActivos());
    }
    // =========================================================
    // 9. LISTAR CATEGORÍAS ACTIVAS (Para el ComboBox)
    // Ruta: GET http://localhost:8080/api/productos/categorias
    // =========================================================
    @GetMapping("/categorias")
    public ResponseEntity<List<Categoria>> listarCategorias() {
        return ResponseEntity.ok(productoService.listarCategoriasActivas());
    }

}
