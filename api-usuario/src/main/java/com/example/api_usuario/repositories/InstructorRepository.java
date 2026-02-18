package com.example.api_usuario.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api_usuario.models.entities.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {

    List<Instructor> findAllByCourseId(int courseId);
    Instructor findByEmail(String email);
}
