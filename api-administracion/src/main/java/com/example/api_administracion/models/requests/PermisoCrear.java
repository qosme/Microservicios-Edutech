package com.example.api_administracion.models.requests;

import lombok.Data;

@Data
public class PermisoCrear {
    private String nombre;
    private String descripcion;
    private int idRol;
}
