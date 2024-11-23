package com.web.restaurante.proyecto.clases.usuarios;

import lombok.Data;

@Data
public class AgregarSaldo {
    private String id_agregar;
    private String id_usuario;
    private String id_administrador;
    private double saldo_agregar;
    private int id_tipo_moneda;
    private int id_estado;
}
