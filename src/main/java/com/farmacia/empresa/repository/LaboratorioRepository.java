package com.farmacia.empresa.repository;

import com.farmacia.empresa.entity.Laboratorio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LaboratorioRepository extends JpaRepository<Laboratorio, Long> {

    List<Laboratorio> findByEstaActivoTrue();

}
