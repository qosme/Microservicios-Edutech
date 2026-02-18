package com.example.api_educacion.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api_educacion.models.entities.Contenido;
import com.example.api_educacion.models.requests.ContenidoCrear;
import com.example.api_educacion.models.requests.ContenidoModificar;
import com.example.api_educacion.services.ContenidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Contenidos", description = "Operaciones relacionadas con contenidos")
@RequestMapping("/contenidos")
@RestController
public class ContenidoController {
    @Autowired
    private ContenidoService contenidoService;


    @Operation(summary = "Mostrar contenidos", description = "Muestra todos los contenidos disponibles")
    @GetMapping("/todos")
    public List<Contenido> listarTodos(){
        return contenidoService.obtenerTodos();
    }

    @Operation(summary = "Nuevo contenido", description = "Crea un nuevo contenido")
    @PostMapping("/nuevo")
    public Contenido crearNuevo(@Valid @RequestBody ContenidoCrear cuerpo){
        return contenidoService.crearNuevo(cuerpo);
    }

    @Operation(summary = "Eliminar contenido", description = "Elimina un contenido por su ID")
    @DeleteMapping("/eliminar/{id}")
    public String eliminarContenido(@PathVariable int id){
        contenidoService.eliminar(id);
        return "Contenido eliminado correctamente";
    }

    @Operation(summary = "Modificar contenido", description = "Modifica un contenido existente")
    @PutMapping("/modificar")
    public Contenido modificar(@RequestBody ContenidoModificar cuerpo){
        return contenidoService.modificar(cuerpo);
    }
}
