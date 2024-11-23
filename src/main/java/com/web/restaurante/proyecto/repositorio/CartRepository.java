package com.web.restaurante.proyecto.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web.restaurante.proyecto.clases.Cart;

import jakarta.transaction.Transactional;
@Repository
public interface CartRepository extends JpaRepository<Cart,String>{
    @Query("SELECT u FROM Cart u WHERE u.id_compra = :id_compra")
    List<Cart> misCompras(@Param("id_compra") String id_compra);    
    @Modifying
    @Transactional
    @Query("INSERT INTO Cart (id_compra, id_producto, nombre_producto, descripcion, cantidad_carrito, precio_carrito, subtotal, img) VALUES (:id_compra, :id_producto, :nombre_producto, :descripcion, :cantidad_carrito, :precio_carrito, :subtotal, :img)")
    void insertCarrito(
    @Param("id_compra") String id_compra, 
    @Param("id_producto") String id_producto, 
    @Param("nombre_producto") String nombre_producto, 
    @Param("descripcion") String descripcion, 
    @Param("cantidad_carrito") int cantidad_carrito, 
    @Param("precio_carrito") double precio_carrito,
    @Param("subtotal") double subtotal, 
    @Param("img") String img);
}
