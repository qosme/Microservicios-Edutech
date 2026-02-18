package com.example.api_usuario.http.response;

import java.util.List;

import com.example.api_usuario.dto.PermisoDTO;
import com.example.api_usuario.dto.RolDTO;
import com.example.api_usuario.models.entities.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRolPermisosResponse {
    private User user;
    private RolDTO rol;
    private List<PermisoDTO> permisos;
}
