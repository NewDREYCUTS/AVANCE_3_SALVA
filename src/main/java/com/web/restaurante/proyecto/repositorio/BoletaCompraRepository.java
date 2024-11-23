package com.web.restaurante.proyecto.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web.restaurante.proyecto.clases.BoletaCompra;

@Repository
public interface BoletaCompraRepository extends JpaRepository<BoletaCompra, String> {
    
    @Query("SELECT u FROM BoletaCompra u WHERE u.id_usuario = :id_usuario")
    List<BoletaCompra> misCompras(@Param("id_usuario") String id_usuario);
}

