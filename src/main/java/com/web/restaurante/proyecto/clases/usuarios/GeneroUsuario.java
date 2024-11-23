package com.web.restaurante.proyecto.clases.usuarios;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "genero_usuario")
public class GeneroUsuario {
    @Id
    private int id_genero;
    private String nombre_genero;
}
