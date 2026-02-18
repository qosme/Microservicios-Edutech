package com.example.api_administracion;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.api_administracion.models.entities.Permiso;
import com.example.api_administracion.models.requests.PermisoCrear;
import com.example.api_administracion.services.PermisoService;

@SpringBootTest
 class PermisoServiceTests {
    @Autowired
    private PermisoService permisoService;

    @Test
    void testCrearPermiso(){
        String nombre = "Permiso de prueba";
        PermisoCrear permisoCrear = new PermisoCrear();
        permisoCrear.setNombre(nombre);
        Permiso PermisoEnDB = permisoService.crear(permisoCrear);
        assertEquals(PermisoEnDB.getNombre(), nombre);
    }
}
