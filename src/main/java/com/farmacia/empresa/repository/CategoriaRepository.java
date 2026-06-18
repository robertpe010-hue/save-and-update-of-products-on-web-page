package com.farmacia.empresa.repository;

import com.farmacia.empresa.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findByEstaActivoTrue();

    List<Categoria> findByCategoriaPadreId(Long parentId); ///Agregacion, esto nos devuelve las subcategorias de un padre, eliminar en caso nno se necesite
}
