package com.farmacia.empresa.service;

import com.farmacia.empresa.dto.ProductoCreacionDTO;
import com.farmacia.empresa.dto.ProductoVistaAdministradorDTO;
import com.farmacia.empresa.dto.ProductoVistaUsuarioDTO;
import com.farmacia.empresa.entity.Categoria;
import com.farmacia.empresa.entity.Laboratorio;
import com.farmacia.empresa.entity.Producto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductoService {
    List<ProductoVistaUsuarioDTO> listarProductoParaTienda();
    List<ProductoVistaAdministradorDTO> listarProductoParaAdmin();

    Producto crearProducto(ProductoCreacionDTO nuevoProducto);
    Producto actualizarProducto(Long idProducto, ProductoCreacionDTO nuevoProducto);

    void desactivarProducto(Long idProducto);
    void activarProducto(Long idProducto);
    void eliminarProducto(Long idProducto);

    List<Laboratorio> listarLaboratoriosActivos();
    List<Categoria> listarCategoriasActivas();
}
