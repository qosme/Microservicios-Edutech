package com.example.api_usuario.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.api_usuario.models.entities.Instructor;
import com.example.api_usuario.models.requests.InstructorCreate;
import com.example.api_usuario.models.requests.InstructorUpdate;
import com.example.api_usuario.repositories.InstructorRepository;

@Service
public class InstructorService {
    @Autowired
    private InstructorRepository instructorRepository;
    
    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    public Instructor getInstructorById(int id) {
        return instructorRepository.findById(id).orElse(null);
    }

    public Instructor register(InstructorCreate instructor){
        try {
            Instructor newInstructor = new Instructor();

            newInstructor.setName(instructor.getName());
            newInstructor.setLastName(instructor.getLastName());
            newInstructor.setSecondLastName(instructor.getSecondLastName());
            newInstructor.setEmail(instructor.getEmail());
            newInstructor.setPassword(passwordHashing(instructor.getPassword()));
            newInstructor.setCourseId(instructor.getCourseId());

            return instructorRepository.save(newInstructor);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Instructor Registrado: ");
        }
    }

    public String passwordHashing(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    //para tener una prueba de hasheo de contraseña
    public boolean testPassword(String hash, String password){
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            return passwordEncoder.matches(password, hash);
    }

    public Instructor updateInstructor(InstructorUpdate body) {
        Instructor instructor = instructorRepository.findById(body.getId()).orElse(null);
        if (instructor == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Instructor no encontrado");
        }

        if(body.getName() != null) {
            instructor.setName(body.getName());
        }

        if(body.getLastName() != null) {
            instructor.setLastName(body.getLastName());
        }

        if(body.getSecondLastName() != null) {
            instructor.setSecondLastName(body.getSecondLastName());
        }

        if(body.getPassword() != null) {
            instructor.setPassword(passwordHashing(body.getPassword()));
        }
        
        if(body.getCourseId() != 0) {
            instructor.setCourseId(body.getCourseId());
        }

        return instructorRepository.save(instructor);
    }

    public void deleteInstructor(int id) {
        Instructor instructor = instructorRepository.findById(id).orElse(null);
        if (instructor == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Instructor no encontrado");
        }
        instructorRepository.delete(instructor);
    }

    public List<Instructor> getByCourse(int courseId) {
        return instructorRepository.findAllByCourseId(courseId);
    }
}
