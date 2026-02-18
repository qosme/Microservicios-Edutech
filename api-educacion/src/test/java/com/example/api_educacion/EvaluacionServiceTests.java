package com.example.api_educacion;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.api_educacion.models.entities.Evaluacion;
import com.example.api_educacion.models.requests.EvaluacionCrear;
import com.example.api_educacion.repositories.EvaluacionRepository;
import com.example.api_educacion.services.EvaluacionService;

@SpringBootTest
class EvaluacionServiceTests {
    @Autowired
    private EvaluacionService evaluacionService;

    @Test
    void testCrearEvaluacion(){
        String nombre = "Evaluacion de prueba";
        String descripcion = "Esta es una evaluacion de prueba"; 
        EvaluacionCrear evaluacionCrear = new EvaluacionCrear();
        evaluacionCrear.setTitulo(nombre);
        evaluacionCrear.setDescripcion(descripcion);
        Evaluacion EvaluacionEnDB = evaluacionService.crearNuevo(evaluacionCrear);
        assertEquals(EvaluacionEnDB.getTitulo(), nombre);
        assertEquals(EvaluacionEnDB.getDescripcion(), descripcion);
    }
}
