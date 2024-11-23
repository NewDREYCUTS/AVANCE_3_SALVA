package com.web.restaurante.proyecto.controlador;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.restaurante.proyecto.Implemento.AddImplement;
import com.web.restaurante.proyecto.Implemento.GeneradorCod;
import com.web.restaurante.proyecto.Implemento.ProductoAdminImplento;
import com.web.restaurante.proyecto.Implemento.ProductoImplemento;
import com.web.restaurante.proyecto.Implemento.UsuarioAdminImplemento;
import com.web.restaurante.proyecto.Implemento.UsuarioImplemento;
import com.web.restaurante.proyecto.clases.productos.Producto;
import com.web.restaurante.proyecto.clases.usuarios.Usuario;
import com.web.restaurante.proyecto.clases.usuarios.UsuarioSaldo;

@RestController
@RequestMapping("/api/v1")
public class Api {

    @Autowired
    private UsuarioAdminImplemento usuarioAdminImplemento;
    @Autowired
    private ProductoAdminImplento productoAdminImplento;

    @Autowired
    private ProductoImplemento productoImplemento;

    @Autowired
    private AddImplement addImplement;
    @Autowired
    private UsuarioImplemento usuarioImplemento;
    GeneradorCod cod = new GeneradorCod();

    @GetMapping("/productos")
    public ArrayList<Producto> productos(Model model) {
        ArrayList<Producto> listaProductos = productoImplemento.listarProducto();
        return listaProductos;
    }

    @GetMapping("/usuarios")
    public ArrayList<Usuario> usuarios(Model model) {
        ArrayList<Usuario> listaUsuario = usuarioAdminImplemento.listaUsuario();
        return listaUsuario;
    }

    @PostMapping("/registrar/usuario")
    public boolean registrarAdminUsuario(
            @RequestBody Usuario usuario) {
        Usuario user = new Usuario();
        System.out.println("usuario: " + usuario);
        user.setId_usuario(cod.codUsuarios());
        user.setId_documento(usuario.getId_documento());
        user.setNumero_documento(usuario.getNumero_documento());
        user.setNombre(usuario.getNombre());
        user.setPrimer_apellido(usuario.getPrimer_apellido());
        user.setSegundo_apellido(usuario.getSegundo_apellido());
        user.setTelefono(usuario.getTelefono());
        user.setCorreo(usuario.getCorreo());
        user.setContrasena(usuario.getContrasena());
        user.setId_genero(usuario.getId_genero());
        user.setId_rol(usuario.getId_rol());
        user.setId_estado(usuario.getId_estado());
        UsuarioSaldo saldo = new UsuarioSaldo();
        saldo.setId_usuario(user.getId_usuario());
        saldo.setSaldo_usuario(0.0);
        boolean res = usuarioAdminImplemento.editarUsuario(user);

        return true;
    }

    @GetMapping("/eliminar/producto/{id_producto}")
    public boolean eliminarProducto(@PathVariable String id_producto) {
        boolean res = productoAdminImplento.eliminarProducto(id_producto);
        if (true) {
            return true;
        } else {
            return false;
        }

    }
}
