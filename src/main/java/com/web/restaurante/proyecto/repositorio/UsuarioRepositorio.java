package com.web.restaurante.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web.restaurante.proyecto.clases.usuarios.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String>{
    @Query("SELECT u FROM Usuario u WHERE u.numero_documento = :numero_documento and u.contrasena = :contrasena")
    Usuario findUserByCodigo(@Param("numero_documento") String numero_documento, @Param("contrasena") String contrasena);

    @Query("SELECT u FROM Usuario u WHERE u.numero_documento = :numero_documento")
    Usuario findUserByDni(@Param("numero_documento") String numero_documento);

    
}
