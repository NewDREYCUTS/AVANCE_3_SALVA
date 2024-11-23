package com.web.restaurante.proyecto.controlador;

import com.web.restaurante.proyecto.reportes.ReporteCompraExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CompraReporteExcelController {

    @Autowired
    private ReporteCompraExcelService reporteCompraExcelService;

    @GetMapping("/administrador/reporte/excel")
    public ResponseEntity<byte[]> descargarReporteExcel() {
        try {
            // Generar el reporte de Excel
            byte[] reporte = reporteCompraExcelService.generarReporteExcelCompras();

            // Configurar los encabezados de la respuesta
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=reporte_compras.xlsx");

            // Retornar el reporte como respuesta
            return new ResponseEntity<>(reporte, headers, HttpStatus.OK);
        } catch (Exception e) {
            // Manejo de errores
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
