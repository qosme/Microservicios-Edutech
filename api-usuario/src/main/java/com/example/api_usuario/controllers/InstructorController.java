package com.example.api_usuario.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api_usuario.assemblers.InstructorModelAssembler;
import com.example.api_usuario.models.entities.Instructor;
import com.example.api_usuario.models.requests.InstructorCreate;
import com.example.api_usuario.models.requests.InstructorUpdate;
import com.example.api_usuario.services.InstructorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import jakarta.validation.Valid;


@CrossOrigin
@RestController
@RequestMapping("/instructores")
@Tag(name = "Instructores", description = "Operaciones relacionadas con instructores")
public class InstructorController {
    @Autowired
    private InstructorService instructorService;

    @Autowired
    private InstructorModelAssembler assembler;

    @Operation(summary = "Mostrar instructores", description = "Muestra todos los instructores")
    @GetMapping("/todos")
    public CollectionModel<EntityModel<Instructor>> getAllInstructors() {
        List<EntityModel<Instructor>> instructores = instructorService.getAllInstructors().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(instructores,
            linkTo(methodOn(InstructorController.class).getAllInstructors()).withSelfRel());
    }

    @Operation(summary = "Buscar instructor por ID", description = "Obtiene un instructor por su ID")
    @GetMapping("/uno/{id}")
    public EntityModel<Instructor> getOneInstructor(@PathVariable int id) {
        Instructor instructor = instructorService.getInstructorById(id);
        return assembler.toModel(instructor);
    }

    @Operation(summary = "Nuevo instructor", description = "Crea un nuevo instructor")
    @PostMapping("/registrar")
    public EntityModel<Instructor> register(@Valid @RequestBody InstructorCreate body) {
        Instructor instructor = instructorService.register(body);
        return assembler.toModel(instructor);
    }


    @Operation(summary = "Actualizar instructor", description = "Actualiza un instructor existente")
    @PutMapping("/actualizar")
    public EntityModel<Instructor> update(@Valid @RequestBody InstructorUpdate body) {
        Instructor instructor = instructorService.updateInstructor(body);
        return assembler.toModel(instructor);
    }

    @Operation(summary = "Eliminar instructor", description = "Elimina un instructor por su ID")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        instructorService.deleteInstructor(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Instructores por curso", description = "Obtiene los instructores por curso")
    @GetMapping("instructor-por-curso/{courseId}")
    public ResponseEntity<?> findByIdCourse(@PathVariable int courseId){
        return ResponseEntity.ok(instructorService.getByCourse(courseId));
    }
    
    
}
