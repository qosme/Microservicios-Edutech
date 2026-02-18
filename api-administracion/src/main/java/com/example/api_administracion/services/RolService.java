package com.example.api_administracion.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.api_administracion.models.entities.Permiso;
import com.example.api_administracion.models.entities.Reporte;
import com.example.api_administracion.models.entities.Rol;
import com.example.api_administracion.models.requests.PermisoCrear;
import com.example.api_administracion.models.requests.ReporteModificar;
import com.example.api_administracion.models.requests.RolCrear;
import com.example.api_administracion.models.requests.RolModificar;
import com.example.api_administracion.repositories.RolRepository;

import java.util.List;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    // buscar todos los roles
    public List<Rol> buscarTodos() {
        return rolRepository.findAll();
    }

    // buscar rol por id
    public Rol obtenerPorId(int id){
        Rol rol =  rolRepository.findById(id).orElse(null);
        if(rol == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Rol no encontrado");
        }
        return rol;
    }

    // Crear un rol
    public Rol crear(RolCrear solicitud){
        Rol nuevoRol = new Rol();

        nuevoRol.setNombre(solicitud.getNombre());
        
        return rolRepository.save(nuevoRol);
    }

    // Modificar un rol existente
    public Rol modificar(RolModificar solicitud){
        Rol rol = obtenerPorId(solicitud.getId());

        if(solicitud.getNombre() != null) {
            rol.setNombre(solicitud.getNombre());
        }

        return rolRepository.save(rol);
    }

    // Eliminar un rol
    public void eliminar(int id){
        Rol rol = obtenerPorId(id);
        rolRepository.delete(rol);
    }
}
