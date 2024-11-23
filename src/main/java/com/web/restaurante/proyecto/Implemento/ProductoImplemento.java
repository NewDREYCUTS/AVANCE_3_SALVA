package com.web.restaurante.proyecto.Implemento;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.restaurante.proyecto.clases.Cart;
import com.web.restaurante.proyecto.clases.productos.Producto;
import com.web.restaurante.proyecto.repositorio.ProductoRepository;
import com.web.restaurante.proyecto.servicio.ProductoServicio;

@Service
public class ProductoImplemento  implements ProductoServicio{
    @Autowired
    private ProductoRepository productoRepository;
    @Override
    public ArrayList<Producto> listarProducto() {
      ArrayList<Producto> lista =  (ArrayList<Producto>) productoRepository.findAll();
      return lista;
    }

    @Override
    public Producto buscarProducto(String id_producto) {
      Optional<Producto> productoOptional = productoRepository.findById(id_producto);
    
    if(productoOptional.isPresent()) {
        return productoOptional.get();
    } else {
        
        return null; 
    }
  }
    @Override
    public ArrayList<Cart> carritoCompras(Producto producto) {
      Cart carrito = new Cart();
      ArrayList<Cart> listaCarrito = new ArrayList<>();
          carrito.setId_producto(producto.getId_producto());
              carrito.setNombre_producto(producto.getNombre_producto());
              carrito.setDescripcion(producto.getDescripcion());
              carrito.setCantidad_carrito(1);
              carrito.setPrecio_carrito(producto.getPrecio_unidad());
              carrito.setSubtotal(producto.getPrecio_unidad() * 1);
              listaCarrito.add(carrito);
      System.out.println("cantidad carrito+++" + listaCarrito.size());
  return listaCarrito;      
    }
}
