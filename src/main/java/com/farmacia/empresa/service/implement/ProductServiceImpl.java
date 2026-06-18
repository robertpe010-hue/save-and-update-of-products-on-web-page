package com.farmacia.empresa.service.implement;

import com.farmacia.empresa.dto.ProductoCreacionDTO;
import com.farmacia.empresa.dto.ProductoVistaAdministradorDTO;
import com.farmacia.empresa.dto.ProductoVistaUsuarioDTO;
import com.farmacia.empresa.entity.Categoria;
import com.farmacia.empresa.entity.Laboratorio;
import com.farmacia.empresa.entity.Producto;
import com.farmacia.empresa.repository.CategoriaRepository;
import com.farmacia.empresa.repository.LaboratorioRepository;
import com.farmacia.empresa.repository.ProductoRepository;
import com.farmacia.empresa.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductoService {

        @Autowired
        private ProductoRepository productoRepository;
        @Autowired
        private LaboratorioRepository laboratorioRepository;
        @Autowired
        private CategoriaRepository categoriaRepository;


        @Override
        @Transactional(readOnly = true)
        public List<ProductoVistaUsuarioDTO> listarProductoParaTienda(){

            List<Producto> productosActivos = productoRepository.findByEstaActivoTrue();
            return productosActivos.stream()
                    .map(prod -> {

                        ProductoVistaUsuarioDTO dto = new ProductoVistaUsuarioDTO();
                        dto.setId(prod.getId());
                        dto.setNombrecomercial(prod.getNombrecomercial());
                        dto.setPrincipioactivo(prod.getPrincipioactivo());
                        dto.setImagenurl(prod.getImagenurl());
                        dto.setPrecioigv(prod.getPrecioigv());
                        return dto;

                    }).collect(Collectors.toList());
        }

        @Override
        @Transactional(readOnly = true)
        public List<ProductoVistaAdministradorDTO> listarProductoParaAdmin(){

            List<Producto> todosLosProductos = productoRepository.findAll();  ///traemos a los productos que esten activos y no activos para mostrarlo en el ponel

            return todosLosProductos.stream()
                    .map(prod -> {
                        ProductoVistaAdministradorDTO dto = new ProductoVistaAdministradorDTO();
                        dto.setId(prod.getId());
                        dto.setSku(prod.getSku());
                        dto.setNombrecomercial(prod.getNombrecomercial());

                        dto.setLaboratorio(prod.getLaboratorio().getNombre()); ///Extraemos los nombres de las relaciones para que el admin pueda leerlos
                        dto.setCategoria(prod.getCategoria().getNombre());

                        dto.setPrecioigv(prod.getPrecioigv());
                        dto.setEstaadoactivo(prod.getEstaActivo());
                        return dto;
                    }).collect(Collectors.toList());
        }

        @Override
        @Transactional
        public Producto crearProducto(ProductoCreacionDTO dto) {

            Laboratorio lab = laboratorioRepository.findById(dto.getLaboratorioid())
                    .orElseThrow(() -> new RuntimeException("El laboratorio seleccionado no existe"));

            Categoria cat = categoriaRepository.findById(dto.getCategoriaid())
                    .orElseThrow(() -> new RuntimeException("La categoría seleccionada no existe"));

            Producto p = new Producto();
            p.setSku(dto.getSku());
            p.setNombrecomercial(dto.getNombrecomercial());
            p.setPrincipioactivo(dto.getPrincipioactivo());
            p.setRegistrosanitario(dto.getRegistrosanitario());
            p.setImagenurl(dto.getImagenurl());
            p.setPrecioigv(dto.getPrecioigv());
            p.setLaboratorio(lab);
            p.setCategoria(cat);

            p.setEstaActivo(dto.getEstaactivo() != null ? dto.getEstaactivo() : true);
            return productoRepository.save(p);
        }

        @Override
        @Transactional
        public Producto actualizarProducto(Long idProducto, ProductoCreacionDTO dtoActualizado) {


            Producto p = productoRepository.findById(idProducto)
                    .orElseThrow(() -> new RuntimeException("El producto que intentas actualizar no existe"));

            p.setNombrecomercial(dtoActualizado.getNombrecomercial());
            p.setPrincipioactivo(dtoActualizado.getPrincipioactivo());
            p.setPrecioigv(dtoActualizado.getPrecioigv());
            p.setImagenurl(dtoActualizado.getImagenurl());
            p.setRegistrosanitario(dtoActualizado.getRegistrosanitario());

            if (!p.getLaboratorio().getId().equals(dtoActualizado.getLaboratorioid())) {
                Laboratorio nuevoLab = laboratorioRepository.findById(dtoActualizado.getLaboratorioid())
                        .orElseThrow(() -> new RuntimeException("El nuevo laboratorio no existe"));
                p.setLaboratorio(nuevoLab);
            }

            if (!p.getCategoria().getId().equals(dtoActualizado.getCategoriaid())) {
                Categoria nuevaCat = categoriaRepository.findById(dtoActualizado.getCategoriaid())
                        .orElseThrow(() -> new RuntimeException("La nueva categoría no existe"));
                p.setCategoria(nuevaCat);
            }

            if(dtoActualizado.getEstaactivo() != null) {
                p.setEstaActivo(dtoActualizado.getEstaactivo());
            }
            return productoRepository.save(p);
        }

        @Override
        @Transactional
        public void desactivarProducto(Long idProducto) {
            Producto p = productoRepository.findById(idProducto).orElseThrow(() -> new RuntimeException("Producto no existe"));
            p.setEstaActivo(false);
            productoRepository.save(p);
        }

        @Override
        @Transactional
        public void activarProducto(Long idProducto) {
            Producto p = productoRepository.findById(idProducto).orElseThrow(() -> new RuntimeException("Producto no existe"));

            p.setEstaActivo(true);
            productoRepository.save(p);
        }

        @Override
        @Transactional
        public void eliminarProducto(Long idProducto) {
            productoRepository.deleteById(idProducto);
        }

        @Override
        @Transactional(readOnly = true)
        public List<Laboratorio> listarLaboratoriosActivos() {
        return laboratorioRepository.findByEstaActivoTrue();
        }

        @Override
        @Transactional(readOnly = true)
        public List<Categoria> listarCategoriasActivas() {
        return categoriaRepository.findByEstaActivoTrue();
        }
}

