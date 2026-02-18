package com.example.api_educacion.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.api_educacion.client.InstructorClient;
import com.example.api_educacion.client.UserClient;
import com.example.api_educacion.dto.InstructorDTO;
import com.example.api_educacion.dto.UserDTO;
import com.example.api_educacion.http.response.InstructorByCursoResponse;
import com.example.api_educacion.http.response.UserByCursoResponse;
import com.example.api_educacion.models.entities.Curso;
import com.example.api_educacion.models.requests.CursoCrear;
import com.example.api_educacion.repositories.CursoRepository;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UserClient userClient;

    @Autowired
    private InstructorClient instructorClient;

    public Curso obtenerPorId(int id) {
        Curso curso = cursoRepository.findById(id).orElse(null);
        if(curso == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Curso no encontrado");
        }
        return curso;
    }

    public List<Curso> obtenerTodos(){
        return cursoRepository.findAll();
    }

    public Curso crearNuevo(CursoCrear solicitud){
        Curso nuevo = new Curso();
        nuevo.setNombre(solicitud.getNombre());
        return cursoRepository.save(nuevo);
    }

    public UserByCursoResponse findUserByIdCurso(int courseId) {
        //consultar curso
        Curso curso = cursoRepository.findById(courseId).orElse(new Curso());

        //obtener usuarios
        //consultar microservicio de usuarios
        List<UserDTO> userDTOList = userClient.findAllUsersByCourse(courseId);

        return UserByCursoResponse.builder()
            .nombre(curso.getNombre())
            .userDTOlist(userDTOList)
            .build();
    }

    public InstructorByCursoResponse findInstructorByIdCurso(int courseId) {
        //consultar curso
        Curso curso = cursoRepository.findById(courseId).orElse(new Curso());

        //obtener usuarios
        //consultar microservicio de usuarios
        List<InstructorDTO> instructorDTOList = instructorClient.findAllInstructorsByCourse(courseId);

        return InstructorByCursoResponse.builder()
            .nombre(curso.getNombre())
            .instructorDTOlist(instructorDTOList)
            .build();
    }


}
