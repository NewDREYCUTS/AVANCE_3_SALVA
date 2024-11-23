package com.web.restaurante.proyecto.controlador;

import com.web.restaurante.proyecto.clases.usuarios.Usuario;
import com.web.restaurante.proyecto.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/validar-dni")
public class DniController {

    @Autowired
    private UsuarioServicio usuarioServicio; // Aquí injectas el servicio

    @GetMapping("/{dni}")
    public ResponseEntity<Map<String, Object>> validarDNI(@PathVariable String dni) {
        Map<String, Object> response = new HashMap<>();

        // Intentamos encontrar el usuario por el DNI
        Usuario usuario = usuarioServicio.findUserByDni(dni);

        // Si el usuario existe, respondemos con 'true', sino 'false'
        if (usuario != null) {
            response.put("existe", true);
        } else {
            response.put("existe", false);
        }

        return ResponseEntity.ok(response);
    }
}
