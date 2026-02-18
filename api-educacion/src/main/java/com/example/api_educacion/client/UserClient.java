package com.example.api_educacion.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.api_educacion.dto.UserDTO;

@FeignClient(name = "api-usuario", url = "http://localhost:8080/usuarios")
public interface UserClient {
    
    @GetMapping("/buscar-por-curso/{courseId}")
    List<UserDTO> findAllUsersByCourse(@PathVariable("courseId") int courseId);
}
