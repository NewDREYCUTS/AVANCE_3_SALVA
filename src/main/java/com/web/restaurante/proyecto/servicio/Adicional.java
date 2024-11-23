package com.web.restaurante.proyecto.servicio;

import java.util.ArrayList;

import com.web.restaurante.proyecto.clases.productos.CategoriaProducto;
import com.web.restaurante.proyecto.clases.usuarios.Estado;
import com.web.restaurante.proyecto.clases.usuarios.GeneroUsuario;
import com.web.restaurante.proyecto.clases.usuarios.RolUsuario;
import com.web.restaurante.proyecto.clases.usuarios.TipoDocumento;

public interface Adicional {
    ArrayList<RolUsuario> listaRol();
    ArrayList<Estado> listaEstado();
    ArrayList<TipoDocumento> listaTipoDoc();
    ArrayList<CategoriaProducto> listaCategoria();
    ArrayList<GeneroUsuario> listaGen();
    boolean agregarCategoria(String categoria);
    boolean eliminarCategoria(int id_categoria);
}
