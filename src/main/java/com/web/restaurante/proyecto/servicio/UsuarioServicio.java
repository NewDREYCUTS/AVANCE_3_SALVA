package com.web.restaurante.proyecto.servicio;

import java.util.ArrayList;

import com.web.restaurante.proyecto.clases.BoletaCompra;
import com.web.restaurante.proyecto.clases.CarritoCompras;
import com.web.restaurante.proyecto.clases.usuarios.Usuario;
import com.web.restaurante.proyecto.clases.usuarios.UsuarioSaldo;

public interface UsuarioServicio {
    Usuario validacionUsuario(String numero_documento, String contrasena);
    Usuario findUserByDni(String numero_documento);  // validar solo DNI
    UsuarioSaldo mySaldo(String id_usuario);
    ArrayList<BoletaCompra> misCompras(String id_usuario);
    ArrayList<CarritoCompras> buscarMicompra(String id_compra);
    boolean agregarCompraCarrito(String Id_compra, String Id_producto, String Nombre_producto, String Descripcion, int Cantidad_carrito, double Precio_carrito, double  Subtotal, String Img);
    boolean actualizarSaldo(UsuarioSaldo usuarioSaldo);
    boolean comprarProductos(BoletaCompra boleta);

}
