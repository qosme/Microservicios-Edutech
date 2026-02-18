package com.example.api_administracion.models.requests;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ReporteModificar {
    private int id;
    private String titulo;
    private String descripcion;
    private String estado;
    private String tipo;
}
