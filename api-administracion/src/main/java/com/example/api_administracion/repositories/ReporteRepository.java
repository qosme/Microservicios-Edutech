package com.example.api_administracion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api_administracion.models.entities.Reporte;


@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Integer> {}