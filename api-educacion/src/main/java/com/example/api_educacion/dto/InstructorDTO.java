package com.example.api_educacion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InstructorDTO {
    private String name;
    private String lastName;
    private String secondLastName;
    private String email;
    private String password;
    private int courseId;
}
