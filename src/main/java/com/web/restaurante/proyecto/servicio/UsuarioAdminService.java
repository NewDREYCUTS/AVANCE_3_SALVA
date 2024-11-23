package com.web.restaurante.proyecto.servicio;

import java.util.ArrayList;

import com.web.restaurante.proyecto.clases.BoletaCompra;
import com.web.restaurante.proyecto.clases.usuarios.ReclamoUsuario;
import com.web.restaurante.proyecto.clases.usuarios.Usuario;
import com.web.restaurante.proyecto.clases.usuarios.UsuarioSaldo;

public interface UsuarioAdminService {
    ArrayList<Usuario> listaUsuario();
    Usuario buscarUsuario(String id_usuario);
    boolean editarUsuario(Usuario usuario);
    boolean eliminarUsuario(String id_usuario);

    ArrayList<ReclamoUsuario> listaReclamos();
    ReclamoUsuario reclamo(String id_reclamo);
    boolean actualizarReclamo(ReclamoUsuario reclamoUsuario);
    boolean usuarioSaldoAdd(UsuarioSaldo usuarioSaldo);
    ArrayList<BoletaCompra> compras();
}
