package com.web.restaurante.proyecto.Implemento;

import java.util.ArrayList;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.restaurante.proyecto.clases.BoletaCompra;
import com.web.restaurante.proyecto.clases.usuarios.ReclamoUsuario;
import com.web.restaurante.proyecto.clases.usuarios.Usuario;
import com.web.restaurante.proyecto.clases.usuarios.UsuarioSaldo;
import com.web.restaurante.proyecto.repositorio.BoletaCompraRepository;
import com.web.restaurante.proyecto.repositorio.ReclamoUsuarioRepository;
import com.web.restaurante.proyecto.repositorio.UsuarioRepositorio;
import com.web.restaurante.proyecto.repositorio.UsuarioSaldoRepository;
import com.web.restaurante.proyecto.servicio.UsuarioAdminService;

@Service
public class UsuarioAdminImplemento  implements UsuarioAdminService{
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private BoletaCompraRepository boletaCompraRepository;
    @Autowired
    private ReclamoUsuarioRepository reclamoUsuarioRepository;

    @Autowired
    private UsuarioSaldoRepository usuarioSaldoRepository;
    @Override
    public ArrayList<Usuario> listaUsuario() {
        ArrayList<Usuario> listaUsuario =(ArrayList<Usuario>) usuarioRepositorio.findAll();
        
        return listaUsuario;
    }
    @Override
    public Usuario buscarUsuario(String id_usuario){
        Optional<Usuario> user =usuarioRepositorio.findById(id_usuario);
            if(user.isPresent()){
                return  user.get();
            }else{
                    return  null;

            }
    }
    @Override
    public boolean editarUsuario(Usuario usuario) {
       usuarioRepositorio.save(usuario);
        return true;
    }

    @Override
    public ArrayList<ReclamoUsuario> listaReclamos() {
        // TODO Auto-generated method stub
        ArrayList<ReclamoUsuario> lista =(ArrayList<ReclamoUsuario>)reclamoUsuarioRepository.findAll();
        return lista;
    }
    @Override
    public ReclamoUsuario reclamo(String id_reclamo) {
        Optional<ReclamoUsuario> reclamo = reclamoUsuarioRepository.findById(id_reclamo);
        if(reclamo.isPresent()){
            return reclamo.get();
        }else{
            return null;
        }
  
    }
    @Override
    public boolean actualizarReclamo(ReclamoUsuario reclamoUsuario) {
       
       reclamoUsuarioRepository.save(reclamoUsuario);
        return true;
    }
    @Override
    public ArrayList<BoletaCompra> compras() {
        ArrayList<BoletaCompra> boleta = (ArrayList<BoletaCompra>) boletaCompraRepository.findAll();
        return boleta;
    }
    
    @Override
    public boolean usuarioSaldoAdd(UsuarioSaldo usuarioSaldo){
        usuarioSaldoRepository.save(usuarioSaldo);
        return true;
    }

    @Override
    public boolean eliminarUsuario(String id_usuario) {
        usuarioRepositorio.deleteById(id_usuario);
        return true;
    }
}
