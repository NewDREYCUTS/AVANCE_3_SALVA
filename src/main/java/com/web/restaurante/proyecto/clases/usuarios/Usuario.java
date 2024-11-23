package com.web.restaurante.proyecto.clases.usuarios;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="usuario")
public class Usuario {
    @Id
    private String id_usuario;
    private int id_documento;
    private String numero_documento;
    private String nombre;
    private String primer_apellido;
    private String segundo_apellido;
    private String telefono;
    private String correo;
    private String contrasena;
    private int id_genero;
    private int id_rol;
    private int id_estado;
}
