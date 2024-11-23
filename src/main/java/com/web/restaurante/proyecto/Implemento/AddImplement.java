package com.web.restaurante.proyecto.Implemento;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.restaurante.proyecto.clases.productos.CategoriaProducto;
import com.web.restaurante.proyecto.clases.usuarios.Estado;
import com.web.restaurante.proyecto.clases.usuarios.GeneroUsuario;
import com.web.restaurante.proyecto.clases.usuarios.RolUsuario;
import com.web.restaurante.proyecto.clases.usuarios.TipoDocumento;
import com.web.restaurante.proyecto.repositorio.CategoriaRepository;
import com.web.restaurante.proyecto.repositorio.EstadoRepository;
import com.web.restaurante.proyecto.repositorio.GeneroUsuarioRepository;
import com.web.restaurante.proyecto.repositorio.RolusuarioRepositor;
import com.web.restaurante.proyecto.repositorio.TipoDocumentoRepository;
import com.web.restaurante.proyecto.servicio.Adicional;

@Service
public class AddImplement implements Adicional {

    @Autowired
    private RolusuarioRepositor rolusuarioRepositorl;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private TipoDocumentoRepository tipoDoc;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private GeneroUsuarioRepository generoUsuarioRepository;
    @Override
    public ArrayList<RolUsuario> listaRol() {
        ArrayList<RolUsuario> lista = (ArrayList<RolUsuario>) rolusuarioRepositorl.findAll();
        return lista;
    }

    @Override
    public ArrayList<Estado> listaEstado() {
        ArrayList<Estado> lista = (ArrayList<Estado>) estadoRepository.findAll();
        return lista;
    }

    @Override
    public ArrayList<TipoDocumento> listaTipoDoc() {
        ArrayList<TipoDocumento> lista = (ArrayList<TipoDocumento>) tipoDoc.findAll();
        return lista;
    }

    @Override
    public ArrayList<CategoriaProducto> listaCategoria() {
        ArrayList<CategoriaProducto> lista = (ArrayList<CategoriaProducto>) categoriaRepository.findAll();
        return lista;
    }

    @Override
    public ArrayList<GeneroUsuario> listaGen() {
        ArrayList<GeneroUsuario> lista = (ArrayList<GeneroUsuario>) generoUsuarioRepository.findAll();
        return lista;
    }

    @Override
    public boolean agregarCategoria(String categoria) {
        CategoriaProducto cat = new CategoriaProducto();
        cat.setNombre_categoria(categoria);
        categoriaRepository.save(cat);
        return true;
    }

    @Override
    public boolean eliminarCategoria(int id_categoria) {
        categoriaRepository.deleteById(id_categoria);
       return true;
    }

    

}
