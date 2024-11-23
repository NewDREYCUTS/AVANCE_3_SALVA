package com.web.restaurante.proyecto.repositorio;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web.restaurante.proyecto.clases.CarritoCompras;
@Repository
public interface CarritoCompraRepository extends JpaRepository<CarritoCompras,Integer>{
    @Query("SELECT u FROM CarritoCompras u WHERE u.id_compra = :id_compra")
    ArrayList<CarritoCompras> misCompras(@Param("id_compra") String id_compra);
}
