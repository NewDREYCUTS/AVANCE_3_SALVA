package com.web.restaurante.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.restaurante.proyecto.clases.usuarios.GeneroUsuario;

@Repository
public interface GeneroUsuarioRepository  extends JpaRepository<GeneroUsuario, Integer>{
    
}
