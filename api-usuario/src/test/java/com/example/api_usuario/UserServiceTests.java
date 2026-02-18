package com.example.api_usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.api_usuario.models.entities.User;
import com.example.api_usuario.models.requests.UserCreate;
import com.example.api_usuario.repositories.UserRepository;
import com.example.api_usuario.services.UserService;

@SpringBootTest
class UserServiceTests {
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepository userRepository;

    @Test
    void testPasswordHashing(){

        String passwordToTest = "ClaveTest123";

        String hash = userService.passwordHashing(passwordToTest);

        Boolean coincide = userService.testPassword(hash, passwordToTest);
        //comprobar que las contraseñas son iguales
        //si coinciden deberia ser true.

        //el parametro uno y el dos (1,2) deben ser iguales
        assertEquals(coincide, true);

        Boolean coincide2 = userService.testPassword(hash, "ClaveTest123");
        assertEquals(coincide2, true);
    }

    //prueba de nombre y email, creacion de usuario en base de datos.
    //tal vez falle porque depende de la base de datos.
    @Test
    void testRegister2(){
        String email = "test@test.com";
        String name = "Usuario Test";
        User user = userRepository.findByEmail(email);
        if(user != null){
            userRepository.delete(user);
        }
        UserCreate newUser = new UserCreate();
        newUser.setEmail(email);
        newUser.setPassword("asjda123456");
        newUser.setName(name);

        User UserInDB = userService.register(newUser);
        assertEquals(UserInDB.getEmail(), email);
        assertEquals(UserInDB.getName(), name);
    }

    @Test
    void testPasswordHashing2(){

        String passwordToTest = "Clave123";

        String hash = userService.passwordHashing(passwordToTest);

        Boolean coincide = userService.testPassword(hash, passwordToTest);

        assertEquals(coincide, true);

        Boolean coincide2 = userService.testPassword(hash, "Clave123");
        assertEquals(coincide2, true);
    }


}
