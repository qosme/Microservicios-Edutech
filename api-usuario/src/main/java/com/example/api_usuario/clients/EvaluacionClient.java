package com.example.api_usuario.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.api_usuario.dto.EvaluacionDTO;

@FeignClient(name = "api-educacion", contextId = "api-evaluacion", url = "http://localhost:8083/evaluaciones")
public interface EvaluacionClient {
    @GetMapping("/buscar-por-usuario/{idUsuario}")
    List<EvaluacionDTO> findAllEvaluationsByUser(@PathVariable("idUsuario") int idUsuario);
}
