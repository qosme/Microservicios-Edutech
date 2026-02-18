package com.example.api_administracion.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    @JsonManagedReference
    @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Permiso> permisos = new ArrayList<>();
}

