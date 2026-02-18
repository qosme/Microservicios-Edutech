package com.example.api_administracion.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.api_administracion.models.entities.Permiso;
import com.example.api_administracion.models.requests.PermisoCrear;
import com.example.api_administracion.models.requests.PermisoModificar;
import com.example.api_administracion.services.PermisoService;

import java.util.List;

@RestController
@RequestMapping("/permisos")
@Tag(name = "Permisos", description = "Operaciones relacionadas con permisos")
public class PermisoController {

    @Autowired
    private PermisoService permisoService;

    @Operation(summary = "Crear permiso", description = "Crea un nuevo permiso")
    @PostMapping("/")
    public Permiso crear(@Valid @RequestBody PermisoCrear permiso) {
        return permisoService.crear(permiso);
    }

    @Operation(summary = "Modificar permiso", description = "Modifica un permiso existente por ID")
    @PutMapping("/")
    public Permiso modificar(@RequestBody PermisoModificar permiso) {
        return permisoService.modificar(permiso);
    }

    @Operation(summary = "Eliminar permiso", description = "Elimina un permiso por ID")
    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable int id){
        permisoService.eliminar(id);
        return "Permiso eliminado correctamente";
    }

    @Operation(summary = "Listar permisos", description = "Obtiene la lista de todos los permisos")
    @GetMapping("/")
    public List<Permiso> listar() {
        return permisoService.buscarTodos();
    }

    @Operation(summary = "Buscar permiso por ID", description = "Obtiene un permiso por su ID")
    @GetMapping("/{id}")
    public Permiso buscarPorId(@PathVariable int id) {
        return permisoService.obtenerPorId(id);
    }

    @Operation(summary = "Permisos por rol", description = "Obtiene los permisos de un rol")
    @GetMapping("/por-rol/{rolId}")
    public List<Permiso> permisosPorRol(@PathVariable int rolId) {
        return permisoService.buscarPorRolId(rolId);
    }
}
