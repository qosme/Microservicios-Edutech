package com.example.api_usuario.models.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserCreate {
    private String name;

    private String lastName;

    private String secondLastName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    private String phone;

    @NotBlank
    private String city;

    @NotBlank
    private String region;

    private int courseId;

    private Integer rolId;
}
