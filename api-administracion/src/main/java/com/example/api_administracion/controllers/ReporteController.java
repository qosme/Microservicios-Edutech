package com.example.api_administracion.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.api_administracion.services.ReporteService;

@RestController
@RequestMapping("/api/reportes")
@Tag(name = "Reportes", description = "Operaciones relacionadas con reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @Operation(
        summary = "Descargar PDF de reporte",
        description = "Genera y descarga el PDF de un reporte por su ID"
    )
    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> descargarPdf(@PathVariable int id) {
        byte[] pdf = reporteService.generarReportePdf(id);
        return ResponseEntity.ok()
            .header("Content-Disposition", "attachment; filename=reporte.pdf")
            .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
            .body(pdf);
    }
}