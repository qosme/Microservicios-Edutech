package com.example.api_usuario.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api_usuario.models.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    //buscar todos los estudiantes por id de curso
  List<User> findAllByCourseId(int courseId);
  //buscar usuario con email para la prueba unitaria
  User findByEmail(String email);
}
