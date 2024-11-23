package com.web.restaurante.proyecto.clases.usuarios;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="tipo_documento")
public class TipoDocumento {
    @Id
    private int id_documento;
    private String nombre_documento;
}
