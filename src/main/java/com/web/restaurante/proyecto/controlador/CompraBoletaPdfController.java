package com.web.restaurante.proyecto.controlador;


import com.web.restaurante.proyecto.reportes.ReporteBoletaPdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cliente/detalle/compra")
public class CompraBoletaPdfController {

    @Autowired
    private ReporteBoletaPdfService reporteBoletaPdfService;

    // Ruta para ver/descargar la boleta en PDF
    @GetMapping("/{id_compra}/pdf")
    public ResponseEntity<byte[]> descargarBoletaPDF(@PathVariable String id_compra) {
        try {
            // Generar la boleta en formato PDF
            byte[] pdf = reporteBoletaPdfService.generarBoletaPDF(id_compra);

            // Configurar la respuesta para el archivo PDF
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.attachment().filename("boleta-" + id_compra + ".pdf").build());

            return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
        } catch (Exception e) {
            // Manejo de errores
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
