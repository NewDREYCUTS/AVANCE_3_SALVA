package com.web.restaurante.proyecto.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class web {
    @GetMapping("/")
    private String web_principal(){
        return "web/index";
    }
    @GetMapping("/nosotros")
    public String nosotros() {
        return "cliente/nosotros";
    }
}
