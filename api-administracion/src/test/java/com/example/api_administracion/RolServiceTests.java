package com.example.api_administracion;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.api_administracion.models.entities.Permiso;
import com.example.api_administracion.models.entities.Rol;
import com.example.api_administracion.models.requests.PermisoCrear;
import com.example.api_administracion.models.requests.RolCrear;
import com.example.api_administracion.services.PermisoService;
import com.example.api_administracion.services.RolService;

@SpringBootTest
 class RolServiceTests {
    @Autowired
    private RolService rolService;

    @Test
    void testCrearRol(){
        String nombre = "Rol de prueba";
        RolCrear rolCrear = new RolCrear();
        rolCrear.setNombre(nombre);
        Rol RolEnDB = rolService.crear(rolCrear);
        assertEquals(RolEnDB.getNombre(), nombre);
    }
}
