package com.example.api_usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.api_usuario.models.entities.Instructor;
import com.example.api_usuario.models.requests.InstructorCreate;
import com.example.api_usuario.repositories.InstructorRepository;
import com.example.api_usuario.services.InstructorService;

@SpringBootTest
class InstructorServiceTests {
    @Autowired
    private InstructorService instructorService;
    @Autowired
    private InstructorRepository instructorRepository;
    
    @Test
    void testPasswordHashing(){

        String passwordToTest = "ClaveTestInst123";

        String hash = instructorService.passwordHashing(passwordToTest);

        Boolean coincide = instructorService.testPassword(hash, passwordToTest);
        //comprobar que las contraseñas son iguales
        //si coinciden deberia ser true.

        //el parametro uno y el dos (1,2) deben ser iguales
        assertEquals(coincide, true);

        Boolean coincide2 = instructorService.testPassword(hash, "ClaveTestInst123");
        assertEquals(coincide2, true);
    }

    //prueba de nombre y email, creacion de usuario en base de datos.
    //tal vez falle porque depende de la base de datos.
    @Test
    void testRegister(){
        String email = "testinst@testinst.com";
        String name = "Instructor Test";
        Instructor instructor = instructorRepository.findByEmail(email);
        if(instructor != null){
            instructorRepository.delete(instructor);
        }
        InstructorCreate newInstructor = new InstructorCreate();
        newInstructor.setEmail(email);
        newInstructor.setPassword("dajszds23456");
        newInstructor.setName(name);

        Instructor UserInDB = instructorService.register(newInstructor);
        assertEquals(UserInDB.getEmail(), email);
        assertEquals(UserInDB.getName(), name);
    }
}
