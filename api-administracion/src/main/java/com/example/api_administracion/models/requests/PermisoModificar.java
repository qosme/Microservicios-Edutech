package com.example.api_administracion.models.requests;

import lombok.Data;

@Data
public class PermisoModificar {
    private int id;
    private String nombre;
    private String descripcion;
}
