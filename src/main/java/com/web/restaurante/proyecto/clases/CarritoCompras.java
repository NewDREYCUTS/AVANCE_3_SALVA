package com.web.restaurante.proyecto.clases;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="carrito_compras")
public class CarritoCompras {
    @Id
    private int id;
    private String id_compra;
    private String id_producto;
    private String nombre_producto;
    private String descripcion;
    private int cantidad_carrito;
    private double precio_carrito;
    private double subtotal;
    private String img;
}
