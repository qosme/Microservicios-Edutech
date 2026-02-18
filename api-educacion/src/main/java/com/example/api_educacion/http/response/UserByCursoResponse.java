package com.example.api_educacion.http.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.example.api_educacion.dto.UserDTO;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserByCursoResponse {
    private String nombre;
    private List<UserDTO> userDTOlist;
}
