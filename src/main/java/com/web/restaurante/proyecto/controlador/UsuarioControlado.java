package com.web.restaurante.proyecto.controlador;


import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.restaurante.proyecto.Implemento.AddImplement;
import com.web.restaurante.proyecto.Implemento.GeneradorCod;
import com.web.restaurante.proyecto.Implemento.ProductoAdminImplento;
import com.web.restaurante.proyecto.Implemento.ProductoImplemento;
import com.web.restaurante.proyecto.Implemento.UsuarioAdminImplemento;
import com.web.restaurante.proyecto.Implemento.UsuarioImplemento;
import com.web.restaurante.proyecto.clases.BoletaCompra;
import com.web.restaurante.proyecto.clases.CarritoCompras;
import com.web.restaurante.proyecto.clases.Cart;
import com.web.restaurante.proyecto.clases.productos.CategoriaProducto;
import com.web.restaurante.proyecto.clases.productos.Producto;
import com.web.restaurante.proyecto.clases.usuarios.Usuario;
import com.web.restaurante.proyecto.clases.usuarios.UsuarioSaldo;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/cliente")
@Controller
public class UsuarioControlado {
    DecimalFormat df = new DecimalFormat("#.##");
    LocalDate fechaHoy = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    @Autowired
    private ProductoImplemento productoImplemento;

    @Autowired
    private UsuarioImplemento usuarioImplemento;
    
    @Autowired
    private UsuarioAdminImplemento usuarioAdminImplemento;

    @Autowired
    private ProductoAdminImplento productoAdminImplento;
    @PostMapping("/recargar/saldo")
    public String recargarSaldo(HttpSession session, @RequestParam("saldo_usuario") double saldo_usuario) {
        String idUsuario = (String) session.getAttribute("IdUsuario");
        UsuarioSaldo miSaldo = usuarioImplemento.mySaldo(idUsuario);
        double saldoActualizado = saldo_usuario + miSaldo.getSaldo_usuario();

        // Actualizar el saldo en la base de datos
        UsuarioSaldo saldo2 = new UsuarioSaldo();
        saldo2.setId_usuario(idUsuario);
        saldo2.setSaldo_usuario(saldoActualizado);
        usuarioImplemento.actualizarSaldo(saldo2);

        // Actualizar el saldo de la sesión
        session.setAttribute("mySaldo", saldoActualizado);

        return "redirect:/cliente/compras";
    }


    @Autowired
    private AddImplement addImplement;
    @GetMapping("/productos")
    public String productos(Model model) {
        ArrayList<CategoriaProducto> cat = addImplement.listaCategoria();
        ArrayList<Producto> listaProductos = productoImplemento.listarProducto();        
        model.addAttribute("cat", cat);
        model.addAttribute("listaProductos", listaProductos);
        return "/cliente/productos";
    }

    @GetMapping("/login")
    public String login() {
        return "web/login";
    }

    @GetMapping("/registrar")
    public String registrar() {
        return "web/registrate";
    }

    @GetMapping("/compras")
    public String misCompras(HttpSession session) {
        String idUsuario = (String) session.getAttribute("IdUsuario");
        ArrayList<BoletaCompra> misCompras = usuarioImplemento.misCompras(idUsuario);
        session.setAttribute("misCompras", misCompras);
        return "/cliente/compras";
    }

    @GetMapping("/limpiar")
    public String clearCart(HttpSession session) {
        ArrayList<Cart> listaCarrito = (ArrayList<Cart>) session.getAttribute("listaCarrito");

        if(listaCarrito.size() > 1)
        {
            listaCarrito.size();
            return "redirect:/cliente/productos";
        }else{
            return "redirect:/cliente/productos";
        }
       
    }
    
    @GetMapping("/detalle/compra/{id_compra}")
    public String detalleCompra(@PathVariable("id_compra") String id_compra, Model model) {
        ArrayList<CarritoCompras> miCompra = usuarioImplemento.buscarMicompra(id_compra);
        model.addAttribute("id_compra", id_compra);
        model.addAttribute("miCompra", miCompra);
        double precioFinalcompra = 0.0;
        for (CarritoCompras sumaTT : miCompra) {
            double sumar = sumaTT.getSubtotal();
            precioFinalcompra += sumar;
        }
        model.addAttribute("precioFinalcompra", precioFinalcompra);
        return "/cliente/detallesCompra";
    }
    @GetMapping("/agregar/carrito/{id_producto}")
    public String agregarCarrito(Model model, HttpSession session, @PathVariable String id_producto, RedirectAttributes redirectAttributes) {
        // Obtener el producto con el id proporcionado
        Producto producto = productoImplemento.buscarProducto(id_producto);
        
        // Obtener la lista de carrito de la sesión o crear una nueva si no existe
        ArrayList<Cart> listaCarrito = (ArrayList<Cart>) session.getAttribute("listaCarrito");
        if (listaCarrito == null) {
            listaCarrito = new ArrayList<>();
        }
        
        if (producto != null) {
            // Verificar si el producto ya está en el carrito
            boolean productoEnCarrito = false;
            for (Cart carrito : listaCarrito) {
                if (carrito.getId_producto().equals(producto.getId_producto())) {
                    // Si el producto ya está en el carrito, aumentar la cantidad y actualizar el subtotal
                    if (carrito.getCantidad_carrito() < producto.getCantidad_stock()) {
                        carrito.setCantidad_carrito(carrito.getCantidad_carrito() + 1);
                        carrito.setSubtotal(carrito.getPrecio_carrito() * carrito.getCantidad_carrito());
                    } else {
                        // Si la cantidad en el carrito ya es igual al stock disponible, mostrar mensaje de error
                        redirectAttributes.addFlashAttribute("mensajeError", "¡Producto supera el stock disponible!");
                    }
                    productoEnCarrito = true;
                    break;
                }
            }
            
            // Si el producto no está en el carrito y hay stock disponible, agregarlo como un nuevo elemento
            if (!productoEnCarrito) {
                if (listaCarrito.size() < producto.getCantidad_stock()) {
                    Cart carrito = new Cart();
                    carrito.setId_producto(producto.getId_producto());
                    carrito.setNombre_producto(producto.getNombre_producto());
                    carrito.setDescripcion(producto.getDescripcion());
                    carrito.setCantidad_carrito(1);
                    carrito.setPrecio_carrito(producto.getPrecio_unidad());
                    carrito.setSubtotal(producto.getPrecio_unidad() * 1);
                    carrito.setImg(producto.getImg_url());
                    listaCarrito.add(carrito);
                } else {
                    // Si la cantidad de productos en el carrito es igual al stock disponible, mostrar mensaje de error
                    redirectAttributes.addFlashAttribute("mensajeError", "¡Producto supera el stock disponible!");
                }
            }
        } else {
            // Si no se encontró el producto, mostrar mensaje de error
            redirectAttributes.addFlashAttribute("mensajeError", "¡Producto no encontrado!");
        }
        
        // Calcular precios
        double total = 0.0;
        for (Cart carrito : listaCarrito) {
            total += carrito.getSubtotal(); // Sumar los subtotales de cada producto
        }

        double igv = total * 0.18;
        double subTotal =total - igv;
        double precioFinal = total;
        
        // Redondear los valores a dos decimales
        subTotal = Math.round(subTotal * 100.0) / 100.0;
        igv = Math.round(igv * 100.0) / 100.0;
        precioFinal = Math.round(precioFinal * 100.0) / 100.0;
    
        // Guardar los valores en la sesión del usuario
        session.setAttribute("subTotal", subTotal);
        session.setAttribute("PrecioFinal", precioFinal);
        session.setAttribute("igv", igv);
        session.setAttribute("cantCarrito", listaCarrito.size());
        session.setAttribute("listaCarrito", listaCarrito);
        
        // Redirigir a la página de productos
        return "redirect:/cliente/productos";
    }
    

    @GetMapping("/generar/compra")
public String comprarCarrito(RedirectAttributes redirectAttributes, HttpSession session,
        @RequestParam String ubicacion, @RequestParam String referencia) {
    // Obtener los datos necesarios de la sesión
    ArrayList<Cart> listaCarrito = (ArrayList<Cart>) session.getAttribute("listaCarrito");
    String IdUsuario = (String) session.getAttribute("IdUsuario");
    Double subTotal = (Double) session.getAttribute("subTotal");
    Double igv = (Double) session.getAttribute("igv");
    Double PrecioFinal = (Double) session.getAttribute("PrecioFinal");
    Double mySaldo = (Double) session.getAttribute("mySaldo");

    // Verificar que los datos necesarios no sean nulos
    if (IdUsuario != null && !IdUsuario.isEmpty() && subTotal != null && igv != null && PrecioFinal != null
            && mySaldo != null) {
        // Verificar si el saldo es suficiente para realizar la compra
        if (mySaldo >= PrecioFinal) {
            BoletaCompra boleta = new BoletaCompra();
            GeneradorCod gen = new GeneradorCod();
            boleta.setId_compra(gen.GeneradorCodCompras());
            boleta.setId_usuario(IdUsuario);
            boleta.setSubtotal(subTotal);
            boleta.setIgv(igv);
            boleta.setTotal(PrecioFinal);
            boleta.setDireccion_entrega(ubicacion);
            boleta.setReferencia_entrega(referencia);
            boleta.setId_estado(1);

            // Obtener la fecha actual formateada
            String fechaFormateada = fechaHoy.format(formatter);
            boleta.setFecha_venta(fechaFormateada);
            
            // Realizar la compra y actualizar el saldo del usuario
            boolean respuesta = usuarioImplemento.comprarProductos(boleta);
            double nuevoSaldo = mySaldo - PrecioFinal;
            UsuarioSaldo newSaldo = new UsuarioSaldo();
            newSaldo.setId_usuario(IdUsuario);
            newSaldo.setSaldo_usuario(nuevoSaldo);
            usuarioImplemento.actualizarSaldo(newSaldo);
            
            if (respuesta) {
                // Si la compra fue exitosa, agregar los productos comprados al historial y actualizar el stock
                String id_boleta = boleta.getId_compra();
                for (Cart carrito : listaCarrito) {
                    boolean respuestaCarrito = usuarioImplemento.agregarCompraCarrito(id_boleta,
                            carrito.getId_producto(), carrito.getNombre_producto(),
                            carrito.getDescripcion(), carrito.getCantidad_carrito(), carrito.getPrecio_carrito(),
                            carrito.getSubtotal(), carrito.getImg());
                    Producto producto = productoImplemento.buscarProducto(carrito.getId_producto());
                    producto.setCantidad_stock(producto.getCantidad_stock() - carrito.getCantidad_carrito());
                    productoAdminImplento.saveProducto(producto);
                }
                // Limpiar el carrito después de la compra
                listaCarrito.clear();
                redirectAttributes.addFlashAttribute("mensajeOK", "¡Compra Exitosa! Su pedido estará listo dentro de 15 a 20 minutos");
            } else {
                redirectAttributes.addFlashAttribute("mensajeError",
                        "¡Saldo Insuficiente para Generar esta compra!");
            }
        } else {
            redirectAttributes.addFlashAttribute("mensajeError", "¡Saldo Insuficiente para Generar esta compra!");
        }
    } else {
        redirectAttributes.addFlashAttribute("mensajeError", "¡Necesitas Iniciar Sesión para Generar una compra!");
    }
    return "redirect:/cliente/productos";
}

    @GetMapping("/mostrar/compra")
    private String mostrarCompra() {

        return "cliente/generarCompra";
    
    }

    @GetMapping("/mostrar/previsualizacion")
    private String mostrarPrevisualizacion() {

        return "cliente/previsualizacion";
    
    }

   

    @GetMapping("/eliminar/producto/carrito/{id_producto}")
    public String eliminarProductoDelCarrito(HttpSession session, @PathVariable String id_producto) {
        try {
            ArrayList<Cart> listaCarrito = (ArrayList<Cart>) session.getAttribute("listaCarrito");
            // Buscar el producto por su id_producto y eliminarlo de la lista
            for (int i = 0; i < listaCarrito.size(); i++) {
                if (listaCarrito.get(i).getId_producto().equals(id_producto)) {
                    listaCarrito.remove(i);
                    break; // Una vez eliminado el producto, salir del bucle
                }
            }
            // Recalcular precios
            double total = 0.0;
            for (Cart carrito : listaCarrito) {
                total += carrito.getSubtotal(); // Sumar los subtotales de cada producto
            }
            double igv = total * 0.18;
            double PrecioFinal = total + igv;
            double subTotal = total;
            // Redondear los valores a dos decimales

            subTotal = Double.parseDouble(df.format(subTotal));
            PrecioFinal = Double.parseDouble(df.format(PrecioFinal));
            igv = Double.parseDouble(df.format(igv));
            // Actualizar los precios en la sesión
            session.setAttribute("subTotal", subTotal);
            session.setAttribute("PrecioFinal", PrecioFinal);
            session.setAttribute("igv", igv);
            session.setAttribute("cantCarrito", listaCarrito.size());
            // Actualizar la lista de carrito en la sesión del usuario
            session.setAttribute("listaCarrito", listaCarrito);
            return "redirect:/cliente/productos";
        } catch (Exception e) {
            return "redirect:/cliente/productos";
        }
    }

    @GetMapping("/actualizar/datos")
    public String updateDatosCliente(HttpSession session, Model model)
    {
        String idUsuario = (String) session.getAttribute("IdUsuario");
         Usuario u =  usuarioAdminImplemento.buscarUsuario(idUsuario);
         model.addAttribute("usuario", u);
        return "/cliente/editarDatos";
    }

    @PostMapping("/update/datos")
    public String actualizarDatosCliente(   HttpSession session,             
        @RequestParam int id_documento,
        @RequestParam String numero_documento,
        @RequestParam String nombre,
        @RequestParam String primer_apellido,
        @RequestParam String segundo_apellido,
        @RequestParam String telefono,
        @RequestParam String correo,
        @RequestParam String contrasena,
        @RequestParam int id_genero
    ){
        String idUsuario = (String) session.getAttribute("IdUsuario");
        Usuario  usuario = new Usuario();
        usuario.setId_usuario(idUsuario);
        usuario.setId_documento(id_documento);
        usuario.setNumero_documento(numero_documento);
        usuario.setNombre(nombre);
        usuario.setPrimer_apellido(primer_apellido);
        usuario.setSegundo_apellido(segundo_apellido);
        usuario.setTelefono(telefono);
        usuario.setCorreo(correo);
        usuario.setContrasena(contrasena);
        usuario.setId_genero(id_genero);
        usuario.setId_rol(1);
        usuario.setId_estado(1);
        usuarioAdminImplemento.editarUsuario(usuario);
        return "redirect:/cliente/compras";
    }

}



