package com.example.api_usuario.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EvaluacionDTO {
    private String titulo;
    private String descripcion;
    private Date fecha;
}
