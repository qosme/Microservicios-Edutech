package com.example.api_usuario.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.api_usuario.controllers.InstructorController;
import com.example.api_usuario.models.entities.Instructor;

@Component
public class InstructorModelAssembler implements RepresentationModelAssembler<Instructor, EntityModel<Instructor>>{
    @Override
    public EntityModel<Instructor> toModel(Instructor instructor) {
        return EntityModel.of(instructor,
            linkTo(methodOn(InstructorController.class).getOneInstructor(instructor.getId())).withSelfRel(),
            linkTo(methodOn(InstructorController.class).getAllInstructors()).withRel("instructores")
        );
    }
}
