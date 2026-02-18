package com.example.api_administracion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api_administracion.models.entities.Rol;


@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
}
