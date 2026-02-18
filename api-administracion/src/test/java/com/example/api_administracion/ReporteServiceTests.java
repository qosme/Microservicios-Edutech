package com.example.api_administracion;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.api_administracion.models.entities.Reporte;
import com.example.api_administracion.models.requests.ReporteCrear;
import com.example.api_administracion.services.ReporteService;

@SpringBootTest
 class ReporteServiceTests {
    @Autowired
    private ReporteService reporteService;

    @Test
    void testCrearReporte(){
        String nombre = "Reporte de prueba";
        ReporteCrear reporteCrear = new ReporteCrear();
        reporteCrear.setTitulo(nombre);
        Reporte ReporteEnDB = reporteService.crearNuevo(reporteCrear);
        assertEquals(ReporteEnDB.getTitulo(), nombre);
    }
}
