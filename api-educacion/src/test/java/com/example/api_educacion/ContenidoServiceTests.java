package com.example.api_educacion;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.api_educacion.models.entities.Contenido;
import com.example.api_educacion.models.entities.Evaluacion;
import com.example.api_educacion.models.requests.ContenidoCrear;
import com.example.api_educacion.models.requests.EvaluacionCrear;
import com.example.api_educacion.services.ContenidoService;

@SpringBootTest
 class ContenidoServiceTests {
    @Autowired
    private ContenidoService contenidoService;

    @Test
    void testCrearContenido(){
        String nombre = "Contenido de prueba";
        ContenidoCrear contenidoCrear = new ContenidoCrear();
        contenidoCrear.setTitulo(nombre);
        Contenido ContenidoEnDB = contenidoService.crearNuevo(contenidoCrear);
        assertEquals(ContenidoEnDB.getTitulo(), nombre);
    }
}
