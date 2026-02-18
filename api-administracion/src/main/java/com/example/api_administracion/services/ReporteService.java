package com.example.api_administracion.services;

import com.example.api_administracion.exceptions.ReportePdfException;
import com.example.api_administracion.models.entities.Permiso;
import com.example.api_administracion.models.entities.Reporte;
import com.example.api_administracion.models.entities.Rol;
import com.example.api_administracion.models.requests.PermisoCrear;
import com.example.api_administracion.models.requests.ReporteCrear;
import com.example.api_administracion.models.requests.ReporteModificar;
import com.example.api_administracion.repositories.ReporteRepository;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class ReporteService {
    @Autowired
    private ReporteRepository reporteRepository;

    // buscar todos los reportes
    public List<Reporte> buscarTodos() {
        return reporteRepository.findAll();
    }

    // buscar permiso por id
    public Reporte obtenerPorId(int id){
        Reporte reporte =  reporteRepository.findById(id).orElse(null);
        if(reporte == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Reporte no encontrado");
        }
        return reporte;
    }

    // Crear un reporte
    public Reporte crearNuevo(ReporteCrear solicitud){
        Reporte nuevoReporte = new Reporte();

        nuevoReporte.setTitulo(solicitud.getTitulo());
        nuevoReporte.setDescripcion(solicitud.getDescripcion());
        nuevoReporte.setEstado(solicitud.getEstado());
        nuevoReporte.setFecha(solicitud.getFecha());
        nuevoReporte.setTipo(solicitud.getTipo());

        return reporteRepository.save(nuevoReporte);
    }

    // modificar un reporte existente
    public Reporte modificar(ReporteModificar solicitud){
        Reporte reporte = obtenerPorId(solicitud.getId());

        if(solicitud.getTitulo() != null) {
            reporte.setTitulo(solicitud.getTitulo());
        }
        if(solicitud.getDescripcion() != null) {
            reporte.setDescripcion(solicitud.getDescripcion());
        }
        if(solicitud.getEstado() != null) {
            reporte.setEstado(solicitud.getEstado());
        }
        if(solicitud.getTipo() != null) {
            reporte.setTipo(solicitud.getTipo());
        }

        return reporteRepository.save(reporte);
    }

    // Eliminar un reporte
    public void eliminar(int id){
        Reporte reporte = obtenerPorId(id);
        reporteRepository.delete(reporte);
    }

    // Generar reporte PDF
    public byte[] generarReportePdf(int id) {
        Reporte reporte = reporteRepository.findById(id).orElseThrow();
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, baos);
            document.open();
            document.add(new Paragraph("Reporte"));
            document.add(new Paragraph("Título: " + reporte.getTitulo()));
            document.add(new Paragraph("Descripción: " + reporte.getDescripcion()));
            document.add(new Paragraph("Estado: " + reporte.getEstado()));
            document.add(new Paragraph("Fecha: " + reporte.getFecha()));
            document.add(new Paragraph("Tipo: " + reporte.getTipo()));
            document.close();
            return baos.toByteArray();
        } catch (Exception e) {
            throw new ReportePdfException("Error generando PDF", e);
        }
    }

}
