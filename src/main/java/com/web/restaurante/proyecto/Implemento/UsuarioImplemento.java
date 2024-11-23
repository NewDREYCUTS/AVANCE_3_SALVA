package com.web.restaurante.proyecto.Implemento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.restaurante.proyecto.clases.BoletaCompra;
import com.web.restaurante.proyecto.clases.CarritoCompras;
import com.web.restaurante.proyecto.clases.usuarios.Usuario;
import com.web.restaurante.proyecto.clases.usuarios.UsuarioSaldo;
import com.web.restaurante.proyecto.repositorio.BoletaCompraRepository;
import com.web.restaurante.proyecto.repositorio.CarritoCompraRepository;
import com.web.restaurante.proyecto.repositorio.CartRepository;
import com.web.restaurante.proyecto.repositorio.UsuarioRepositorio;
import com.web.restaurante.proyecto.repositorio.UsuarioSaldoRepository;
import com.web.restaurante.proyecto.servicio.UsuarioServicio;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UsuarioImplemento implements UsuarioServicio {

    @Autowired
    private JdbcTemplate jdbcTemplate; // Inyecta JdbcTemplate

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public Usuario validacionUsuario(String numero_documento, String contrasena) {
        Usuario user = usuarioRepositorio.findUserByCodigo(numero_documento, contrasena);
        try {
            if (!user.getId_usuario().isEmpty()) {
                return user;
            } else {
                return user;
            }
        } catch (Exception e) {
            return user;
        }
    }

    @Override
    public Usuario findUserByDni(String numero_documento) {
        return usuarioRepositorio.findUserByDni(numero_documento); // validar solo DNI
    }

    @Autowired
    private UsuarioSaldoRepository usuarioSaldoRepository;

    @Override
    public UsuarioSaldo mySaldo(String id_usuario) {
        Optional<UsuarioSaldo> saldo = usuarioSaldoRepository.findById(id_usuario);
        if (saldo.isPresent()) {
            return saldo.get();
        } else {
            return null;
        }
    }

    @Autowired
    private BoletaCompraRepository boletaCompraRepository;

    @Override
    public ArrayList<BoletaCompra> misCompras(String id_usuario) {
        ArrayList<BoletaCompra> misCompras = (ArrayList<BoletaCompra>) boletaCompraRepository.misCompras(id_usuario);
        return misCompras;
    }

    @Autowired
    private CarritoCompraRepository carritoCompraRepository;

    @Override
    public ArrayList<CarritoCompras> buscarMicompra(String id_compra) {
        ArrayList<CarritoCompras> miCompras = carritoCompraRepository.misCompras(id_compra);
        return miCompras;
    }

    @Autowired
    private CartRepository cartRepository;

    @Override
    public boolean comprarProductos(BoletaCompra boleta) {
        boletaCompraRepository.save(boleta);
        return true;
    }

    @Override
    public boolean actualizarSaldo(UsuarioSaldo usuarioSaldo) {
        boolean res = true;
        try {
            usuarioSaldoRepository.save(usuarioSaldo);
            return res;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean agregarCompraCarrito(String Id_compra, String Id_producto, String Nombre_producto, String Descripcion,
                                        int Cantidad_carrito, double Precio_carrito, double Subtotal, String Img) {
        cartRepository.insertCarrito(Id_compra, Id_producto, Nombre_producto, Descripcion, Cantidad_carrito, Precio_carrito, Subtotal, Img);
        return true;
    }

    // Método save() que puedes usar para registrar un usuario
//    public void save(Usuario usuario) {
//        usuarioRepositorio.save(usuario); // Guarda al usuario en la base de datos
//    }

 //   @Override
//    public BoletaCompra buscarBoleta(String id_compra) {
//        String sql = "SELECT * FROM boleta_compra WHERE id_compra = ?";
//        return jdbcTemplate.queryForObject(sql, new Object[]{id_compra},
//                (rs, rowNum) -> {
//                    BoletaCompra boleta = new BoletaCompra();
//                    boleta.setId_compra(rs.getString("id_compra"));
//                    boleta.setId_usuario(rs.getString("id_usuario"));
//                    boleta.setSubtotal(rs.getDouble("subtotal"));
//                    boleta.setIgv(rs.getDouble("igv"));
//                    boleta.setTotal(rs.getDouble("total"));
//                    boleta.setDireccion_entrega(rs.getString("direccion_entrega"));
//                    boleta.setReferencia_entrega(rs.getString("referencia_entrega"));
//                    boleta.setFecha_venta(rs.getString("fecha_venta"));
//                    boleta.setId_estado(rs.getInt("id_estado"));
//                    return boleta;
//                });
//    }
}
