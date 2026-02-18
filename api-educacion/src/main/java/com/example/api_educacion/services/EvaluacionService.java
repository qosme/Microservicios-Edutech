package com.example.api_educacion.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.api_educacion.models.entities.Curso;
import com.example.api_educacion.models.entities.Evaluacion;

import com.example.api_educacion.models.requests.EvaluacionCrear;
import com.example.api_educacion.models.requests.EvaluacionModificar;
import com.example.api_educacion.repositories.CursoRepository;
import com.example.api_educacion.repositories.EvaluacionRepository;

@Service
public class EvaluacionService {
    @Autowired
    private EvaluacionRepository evaluacionRepository;
    @Autowired
    private CursoRepository cursoRepository;

    public List<Evaluacion> obtenerTodos(){
        return evaluacionRepository.findAll();
    }

    public Evaluacion obtenerPorId(int id){
        Evaluacion evaluacion =  evaluacionRepository.findById(id).orElse(null);
        if(evaluacion == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Evaluación no encontrada");
        }
        return evaluacion;
    }

    public Evaluacion crearNuevo(EvaluacionCrear solicitud){
        Evaluacion nuevo = new Evaluacion();

        nuevo.setDescripcion(solicitud.getDescripcion());
        nuevo.setTitulo(solicitud.getTitulo());
        
        Curso cursoAsociado = cursoRepository.findById(solicitud.getIdCurso()).orElse(null);
        nuevo.setCurso(cursoAsociado);

        nuevo.setFecha(solicitud.getFecha());
        nuevo.setIdUsuario(solicitud.getIdUsuario());
        return evaluacionRepository.save(nuevo);
    }

    public void eliminar(int id){
        Evaluacion evaluacion = obtenerPorId(id);
        evaluacionRepository.delete(evaluacion);
    }

    public Evaluacion modificar(EvaluacionModificar solicitud){
        Evaluacion evaluacion = obtenerPorId(solicitud.getId());

        if(solicitud.getTitulo() != null){
            evaluacion.setTitulo(solicitud.getTitulo());
        }
        if(solicitud.getDescripcion() != null){
            evaluacion.setDescripcion(solicitud.getDescripcion());
        }
        if(solicitud.getFecha() != null){
            evaluacion.setFecha(solicitud.getFecha());
        }
        if(solicitud.getIdUsuario() > 0){
            evaluacion.setIdUsuario(solicitud.getIdUsuario());
        }
        return evaluacionRepository.save(evaluacion);
    }
    public List<Evaluacion> obtenerPorUsuario(int idUsuario) {
        return evaluacionRepository.findAllByidUsuario(idUsuario);
    }


}
