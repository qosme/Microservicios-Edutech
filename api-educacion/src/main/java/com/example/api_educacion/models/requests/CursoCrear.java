package com.example.api_educacion.models.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CursoCrear {
    @NotBlank
    private String nombre;
}
