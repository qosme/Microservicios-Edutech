package com.example.api_educacion.http.response;

import java.util.List;

import com.example.api_educacion.dto.InstructorDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InstructorByCursoResponse {
    private String nombre;
    private List<InstructorDTO> instructorDTOlist;
}
