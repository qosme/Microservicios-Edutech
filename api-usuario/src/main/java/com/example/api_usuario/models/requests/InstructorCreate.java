package com.example.api_usuario.models.requests;

import lombok.Data;

@Data
public class InstructorCreate {
    private String name;
    private String lastName;
    private String secondLastName;
    private String email;
    private String password;
    private int courseId;
}
