package com.example.api_usuario.models.requests;

import lombok.Data;

@Data
public class AsignarRolRequest {
    /** ID del rol. Use 0 para quitar el rol. */
    private int rolId;
}
