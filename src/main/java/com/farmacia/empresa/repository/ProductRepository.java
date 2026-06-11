package com.farmacia.empresa.repository;

import com.farmacia.empresa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
