package com.example.api_educacion.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.api_educacion.dto.InstructorDTO;


@FeignClient(name = "api-usuario", contextId = "api-instructor", url = "http://localhost:8080/instructores")
public interface InstructorClient {
    @GetMapping("/instructor-por-curso/{courseId}")
    List<InstructorDTO> findAllInstructorsByCourse(@PathVariable("courseId") int courseId);
}
