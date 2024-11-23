package com.web.restaurante.proyecto.clases;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "boleta_compra")
public class BoletaCompra {
    @Id
    private String id_compra;
    private String id_usuario;
    private double subtotal;
    private double igv;
    private double total;
    private int id_estado;
    private String direccion_entrega;
    private String referencia_entrega;
    private String fecha_venta;
}
