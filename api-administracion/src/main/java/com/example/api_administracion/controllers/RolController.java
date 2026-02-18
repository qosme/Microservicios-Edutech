package com.example.api_administracion.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.api_administracion.models.entities.Rol;
import com.example.api_administracion.models.requests.RolCrear;
import com.example.api_administracion.models.requests.RolModificar;
import com.example.api_administracion.services.RolService;

import java.util.List;

@RestController
@RequestMapping("/roles")
@Tag(name = "Roles", description = "Operaciones relacionadas con roles")
public class RolController {

    @Autowired
    private RolService rolService;

    @Operation(summary = "Crear rol", description = "Crea un nuevo rol")
    @PostMapping("/")
    public Rol crear(@Valid @RequestBody RolCrear rol) {
        return rolService.crear(rol);
    }

    @Operation(summary = "Modificar rol", description = "Modifica un rol existente por ID")
    @PutMapping("/")
    public Rol modificar(@RequestBody RolModificar rol) {
        return rolService.modificar(rol);
    }

    @Operation(summary = "Eliminar rol", description = "Elimina un rol por ID")
    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable int id){
        rolService.eliminar(id);
        return "Rol eliminado correctamente";
    }

    @Operation(summary = "Listar roles", description = "Obtiene la lista de todos los roles")
    @GetMapping("/")
    public List<Rol> listar() {
        return rolService.buscarTodos();
    }

    @Operation(summary = "Buscar rol por ID", description = "Obtiene un rol por su ID")
    @GetMapping("/{id}")
    public Rol buscarPorId(@PathVariable int id) {
        return rolService.obtenerPorId(id);
    }
}
