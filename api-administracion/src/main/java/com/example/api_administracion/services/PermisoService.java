package com.example.api_administracion.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.api_administracion.models.entities.Permiso;
import com.example.api_administracion.models.entities.Rol;
import com.example.api_administracion.models.requests.PermisoCrear;
import com.example.api_administracion.models.requests.PermisoModificar;
import com.example.api_administracion.repositories.PermisoRepository;
import com.example.api_administracion.repositories.RolRepository;

import java.util.List;

@Service
public class PermisoService {
    @Autowired
    private PermisoRepository permisoRepository;
    @Autowired
    private RolRepository rolRepository;

    // buscar todos los permisos
    public List<Permiso> buscarTodos() {
        return permisoRepository.findAll();
    }


    // buscar permisos por rol
    public List<Permiso> buscarPorRolId(int rolId) {
        return permisoRepository.findByRol_Id(rolId);
    }

    // buscar permiso por id
    public Permiso obtenerPorId(int id){
        Permiso permiso =  permisoRepository.findById(id).orElse(null);
        if(permiso == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Permiso no encontrado");
        }
        return permiso;
    }

    // Crear un permiso
    public Permiso crear(PermisoCrear solicitud){
        Permiso nuevoPermiso = new Permiso();

        nuevoPermiso.setNombre(solicitud.getNombre());
        nuevoPermiso.setDescripcion(solicitud.getDescripcion());
        
        Rol rolAsociado = rolRepository.findById(solicitud.getIdRol()).orElse(null);

        nuevoPermiso.setRol(rolAsociado);
        return permisoRepository.save(nuevoPermiso);
    }

    // Modificar un permiso existente
    public Permiso modificar(PermisoModificar solicitud){
        Permiso permiso = obtenerPorId(solicitud.getId());

        if(solicitud.getNombre() != null) {
            permiso.setNombre(solicitud.getNombre());
        }
        if(solicitud.getDescripcion() != null) {
            permiso.setDescripcion(solicitud.getDescripcion());
        }

        return permisoRepository.save(permiso);
    }

    // Eliminar un permiso
    public void eliminar(int id){
        Permiso permiso = obtenerPorId(id);
        permisoRepository.delete(permiso);
    }
}

