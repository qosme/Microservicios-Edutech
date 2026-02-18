package com.example.api_usuario.models.requests;

import lombok.Data;

@Data
public class UserUpdate {
    private int id;
    private String name;
    private String lastName;
    private String secondLastName;
    private String password;
    private String phone;
    private String city;
    private String region;
    private int courseId;

    private Integer rolId; // 0 or null = sin rol
}
