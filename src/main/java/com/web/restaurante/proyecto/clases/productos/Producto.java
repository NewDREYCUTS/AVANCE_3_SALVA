package com.web.restaurante.proyecto.clases.productos;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "producto")
@Entity
public class Producto {
    @Id
    private String id_producto;
    
    private String nombre_producto;
    private String descripcion;
    private int cantidad_stock;
    private double precio_unidad;
    private int id_categoria;
    private String img_url;
    private int id_estado;
}
