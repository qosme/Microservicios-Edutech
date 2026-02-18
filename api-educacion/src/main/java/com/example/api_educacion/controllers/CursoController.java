package com.example.api_educacion.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api_educacion.http.response.InstructorByCursoResponse;
import com.example.api_educacion.http.response.UserByCursoResponse;
import com.example.api_educacion.models.entities.Curso;
import com.example.api_educacion.models.requests.CursoCrear;
import com.example.api_educacion.services.CursoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
@Tag(name = "Cursos", description = "Operaciones relacionadas con cursos")
@RequestMapping("/cursos")
@RestController
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @Operation(summary = "Mostrar cursos", description = "Muestra todos los cursos disponibles")
    @GetMapping("/todos")
    public List<Curso> todos(){
        return cursoService.obtenerTodos();
    }

    @Operation(summary = "Listar curso", description = "Obtiene un curso por su ID")
    @GetMapping("/listar/{id}")
    public Curso listarUno(@PathVariable int id){
        return cursoService.obtenerPorId(id);
    }

    @Operation(summary = "Nuevo curso", description = "Crea un nuevo curso")
    @PostMapping("/nuevo")
    public Curso crearNuevo(@Valid @RequestBody CursoCrear cuerpo){
        return cursoService.crearNuevo(cuerpo);
    }

    @Operation(summary = "Usuarios por curso", description = "Obtiene un usuarios por curso")
    @GetMapping("listar-usuarios-curso/{courseId}")
    public UserByCursoResponse findUserByIdCurso(@PathVariable int courseId){
        return cursoService.findUserByIdCurso(courseId);
    }
    @Operation(summary = "Instructores por curso", description = "Obtiene un instructores por curso")
    @GetMapping("listar-instructores-curso/{courseId}")
    public InstructorByCursoResponse findInstructorByIdCurso(@PathVariable int courseId) {
        return cursoService.findInstructorByIdCurso(courseId);
    }

}
