package com.example.api_educacion.models.requests;

import java.sql.Date;

import lombok.Data;

@Data
public class EvaluacionModificar {
    private int id;
    private String titulo;
    private String descripcion;
    private Date fecha;
    private int idUsuario;
}
