package com.web.restaurante.proyecto.Implemento;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.restaurante.proyecto.clases.productos.Producto;
import com.web.restaurante.proyecto.repositorio.ProductoRepository;
import com.web.restaurante.proyecto.servicio.ProductoAdminService;

@Service
public class ProductoAdminImplento  implements ProductoAdminService{


    @Autowired
    private ProductoRepository productoRepository;
    @Override
    public ArrayList<Producto> listaProducto() {
        ArrayList<Producto> lista =(ArrayList<Producto>) productoRepository.findAll();
    return lista;
    }


    @Override
    public boolean saveProducto (Producto producto){
       
        try {
             productoRepository.save(producto);        
            return true;
        } catch (Exception e) {
            return false;
        }
       
    }

//    @Override
//    public Producto buscarProducto(String id_producto){
//        Optional<Producto> producto =productoRepository.findById(id_producto);
//        if(producto.isPresent()){
//            return  producto.get();
//        }else{
//            return  null;
//
//        }
//    }

    @Override
    public boolean eliminarProducto(String id_producto) {
        productoRepository.deleteById(id_producto);
       return true;
    }
}
