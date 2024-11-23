package com.web.restaurante.proyecto.servicio;

import java.util.ArrayList;

import com.web.restaurante.proyecto.clases.Cart;
import com.web.restaurante.proyecto.clases.productos.Producto;

public interface ProductoServicio {
    ArrayList<Producto> listarProducto();
    Producto buscarProducto(String id_producto);   
    ArrayList<Cart> carritoCompras(Producto producto); 
 
}
