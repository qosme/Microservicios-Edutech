package com.example.api_educacion.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String name;

    private String lastName;

    private String secondLastName;

    private String email;

    private String password;

    private String phone;

    private Date createdAt;

    private String city;

    private String region;

    private Boolean isActive;

    private int courseId;
}
