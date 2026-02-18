package com.example.api_usuario.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.api_usuario.dto.PermisoDTO;
import com.example.api_usuario.dto.RolDTO;

@FeignClient(name = "api-administracion", url = "http://localhost:8087")
public interface AdministracionClient {

    @GetMapping("/roles/{id}")
    RolDTO getRolById(@PathVariable("id") int id);

    @GetMapping("/permisos/por-rol/{rolId}")
    List<PermisoDTO> getPermisosByRolId(@PathVariable("rolId") int rolId);
}
