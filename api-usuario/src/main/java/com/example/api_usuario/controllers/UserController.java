package com.example.api_usuario.controllers;

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

import com.example.api_usuario.http.response.EvaluacionByUserResponse;
import com.example.api_usuario.http.response.UserRolPermisosResponse;
import com.example.api_usuario.models.entities.User;
import com.example.api_usuario.models.requests.AsignarRolRequest;
import com.example.api_usuario.models.requests.UserCreate;
import com.example.api_usuario.models.requests.UserUpdate;
import com.example.api_usuario.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuarios", description = "Operaciones relacionadas con usuarios")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "Mostrar usuarios", description = "Muestra todos los alumnos")
    @GetMapping("/todos")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(summary = "Buscar usuario por ID", description = "Obtiene un usuario por su ID")
    @GetMapping("/uno/{id}")
    public User getOneUser(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @Operation(summary = "Registrar usuario", description = "Crea un nuevo usuario")
    @PostMapping("/registrar")
    public User register(@Valid @RequestBody UserCreate body){
        return userService.register(body);
    }

    @Operation(summary = "Actualizar usuario", description = "Actualiza un usuario existente")
    @PutMapping("/actualizar")
    public User update(@Valid @RequestBody UserUpdate body){
        return userService.updateUser(body);
    }

    @Operation(summary = "Eliminar usuario", description = "Elimina un usuario por su ID")
    @DeleteMapping("/eliminar/{id}")
    public String delete(@PathVariable int id) {
        userService.deleteUser(id);
        return "Usuario eliminado";
    }

    @Operation(summary = "Buscar usuario por curso", description = "Obtiene un usuario por su curso")
    @GetMapping("/buscar-por-curso/{courseId}")
    public List<User> getByCourse(@PathVariable int courseId) {
        return userService.getByCourse(courseId);
    }

    @Operation(summary = "Buscar evaluaciones por usuario", description = "Obtiene las evaluaciones por usuario")
    @GetMapping("evaluaciones-por-usuario/{idUsuario}")
    public EvaluacionByUserResponse findEvaluacionByIdUsuario(@PathVariable int idUsuario){
        return userService.findEvaluacionByIdUsuario(idUsuario);
    }

    @Operation(summary = "Asignar o quitar rol a usuario", description = "Asigna un rol (rolId>0) o quita el rol (rolId=0)")
    @PutMapping("/{id}/rol")
    public User assignRol(@PathVariable int id, @RequestBody AsignarRolRequest request) {
        return userService.assignRol(id, request.getRolId());
    }

    @Operation(summary = "Quitar rol de usuario", description = "Elimina el rol asignado a un usuario")
    @DeleteMapping("/{id}/rol")
    public User removeRol(@PathVariable int id) {
        return userService.removeRol(id);
    }

    @Operation(summary = "Usuario con rol y permisos", description = "Obtiene un usuario con su rol y permisos asociados")
    @GetMapping("/{id}/rol-permisos")
    public UserRolPermisosResponse getUserWithRolAndPermisos(@PathVariable int id) {
        return userService.getUserWithRolAndPermisos(id);
    }
}
