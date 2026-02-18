package com.example.api_usuario.http.response;

import java.util.List;

import com.example.api_usuario.dto.EvaluacionDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EvaluacionByUserResponse {
    private String name;
    private String lastName;
    private String secondLastName;
    private String email;
    private String password;
    private String phone;
    private String city;
    private String region;
    private List<EvaluacionDTO> evaluacionDTOlist;
}
