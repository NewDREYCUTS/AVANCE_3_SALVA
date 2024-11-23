package com.web.restaurante.proyecto.controlador;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.restaurante.proyecto.Implemento.UsuarioImplemento;
import com.web.restaurante.proyecto.clases.usuarios.Usuario;
import com.web.restaurante.proyecto.clases.usuarios.UsuarioSaldo;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/autenticacion")
public class Autenticacion {

  @Autowired
  private UsuarioImplemento usuarioImplemento;

  @GetMapping("/registrate")
  public String  registrarUsuario(){

    return "redirect:/";
  }
  @PostMapping("/login")
  public String validacionUsuario(RedirectAttributes redirectAttributes, HttpSession session,
                                  @RequestParam String numero_documento,
                                  @RequestParam String contrasena) {

    if (!numero_documento.isEmpty() && !contrasena.isEmpty()) {
      try {
        Usuario user = usuarioImplemento.validacionUsuario(numero_documento, contrasena);
        UsuarioSaldo mySaldo = usuarioImplemento.mySaldo(user.getId_usuario());
        if (!user.getId_usuario().isEmpty()) {
          String[] name = user.getNombre().split(" ");
          String nombre = name[0];
          session.setAttribute("IdUsuario", user.getId_usuario());
          session.setAttribute("NombreUsuario", nombre);
          session.setAttribute("mySaldo", mySaldo.getSaldo_usuario());
          redirectAttributes.addFlashAttribute("mensajeOK",
                  "Bienvenido: " + user.getNombre().toUpperCase() + " " + user.getPrimer_apellido().toUpperCase());
          if(user.getId_rol() == 1){
            return "redirect:/";
          }else if  (user.getId_rol() == 2){
            return "redirect:/administrador/dashboard";
          }else{
            session.invalidate(); // Invalidar la sesión en caso de error
            redirectAttributes.addFlashAttribute("mensajeError",
                    "El Documento de Usuario No Existe, Por Favor Ingrese Bien sus Datos");
            return "redirect:/cliente/login";
          }

        } else {
          session.invalidate(); // Invalidar la sesión en caso de error
          redirectAttributes.addFlashAttribute("mensajeError",
                  "El Documento de Usuario No Existe, Por Favor Ingrese Bien sus Datos");
          return "redirect:/cliente/login";
        }


      } catch (Exception e) {
        session.invalidate(); // Invalidar la sesión en caso de error
        redirectAttributes.addFlashAttribute("mensajeError",
                "Error al procesar los datos del usuario. Por favor, inténtelo de nuevo.");
        return "redirect:/cliente/login";
      }
    } else {
      session.invalidate(); // Invalidar la sesión en caso de error
      redirectAttributes.addFlashAttribute("mensajeError",
              "Error al procesar los datos del usuario. Por favor, inténtelo de nuevo.");
      return "redirect:/cliente/login";

    }

  }

  @GetMapping("/logout")
  public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:/";
  }
}
