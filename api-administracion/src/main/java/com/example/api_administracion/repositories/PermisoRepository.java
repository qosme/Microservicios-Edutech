package com.example.api_administracion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api_administracion.models.entities.Permiso;

import java.util.List;

@Repository
public interface PermisoRepository extends JpaRepository<Permiso, Integer> {

    List<Permiso> findByRol_Id(int rolId);
}
