package com.web.restaurante.proyecto.clases.usuarios;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="rol_usuario")
public class RolUsuario {
    @Id
    private int id_rol;
    private String nombre_rol;
    
}
