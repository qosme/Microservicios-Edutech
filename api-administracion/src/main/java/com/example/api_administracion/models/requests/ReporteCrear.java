package com.example.api_administracion.models.requests;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ReporteCrear {
    private String titulo;
    private String descripcion;
    private String estado;
    private LocalDateTime fecha;
    private String tipo;
}
