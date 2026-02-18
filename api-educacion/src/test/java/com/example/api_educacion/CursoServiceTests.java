package com.example.api_educacion;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.api_educacion.models.entities.Curso;
import com.example.api_educacion.models.requests.CursoCrear;
import com.example.api_educacion.services.CursoService;

@SpringBootTest
 class CursoServiceTests {
    @Autowired
    private CursoService cursoService;

    @Test
    void testCrearCurso(){
        String nombre = "Curso de prueba";
        CursoCrear cursoCrear = new CursoCrear();
        cursoCrear.setNombre(nombre);
        Curso CursoEnDB = cursoService.crearNuevo(cursoCrear);
        assertEquals(CursoEnDB.getNombre(), nombre);
    }
}
