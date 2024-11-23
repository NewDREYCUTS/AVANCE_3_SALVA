package com.web.restaurante.proyecto.clases.usuarios;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "saldo_usuario")
public class UsuarioSaldo {
    @Id
    private String id_usuario;
    private double saldo_usuario;
}
