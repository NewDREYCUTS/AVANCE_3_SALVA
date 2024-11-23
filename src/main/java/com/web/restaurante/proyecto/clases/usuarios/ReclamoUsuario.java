package com.web.restaurante.proyecto.clases.usuarios;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="reclamo_usuario")
public class ReclamoUsuario {
    @Id
    private String id_reclamo;
    private String id_usuario;
    private String motivo;
    private String descripcion;
    private int id_estado_mensaje;
    private String respuesta;
}
