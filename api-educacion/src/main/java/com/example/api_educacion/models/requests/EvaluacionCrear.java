package com.example.api_educacion.models.requests;

import java.sql.Date;

import lombok.Data;

@Data
public class EvaluacionCrear {
    private String titulo;
    private String descripcion;
    private int idCurso;
    private Date fecha;
    private int idUsuario;
}
