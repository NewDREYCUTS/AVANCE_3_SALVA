package com.web.restaurante.proyecto.reportes;

import com.web.restaurante.proyecto.clases.BoletaCompra;
import com.web.restaurante.proyecto.clases.usuarios.Usuario;
import com.web.restaurante.proyecto.repositorio.BoletaCompraRepository;
import com.web.restaurante.proyecto.repositorio.UsuarioRepositorio;
import com.web.restaurante.proyecto.repositorio.UsuarioRepositorio;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class ReporteCompraExcelService {

    @Autowired
    private BoletaCompraRepository boletaCompraRepository;

    @Autowired
    private UsuarioRepositorio usuarioRepository; // Agregar repositorio de usuarios

    public byte[] generarReporteExcelCompras() {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Reporte de Compras");

            // Crear estilos para las celdas
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);

            CellStyle dateStyle = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
            dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

            // Crear encabezados
            Row headerRow = sheet.createRow(0);
            String[] columnHeaders = {"ID Compra", "ID Usuario", "DNI", "Nombre Usuario", "Apellido Usuario", "Correo", "Teléfono", "Dirección de Entrega", "Fecha de Venta", "Subtotal", "IGV", "Total"};
            for (int i = 0; i < columnHeaders.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columnHeaders[i]);
                cell.setCellStyle(headerStyle);
            }

            // Obtener datos de las compras
            List<BoletaCompra> compras = boletaCompraRepository.findAll();

            // Agregar datos a la hoja
            int rowNum = 1;
            for (BoletaCompra compra : compras) {
                Row row = sheet.createRow(rowNum++);

                // Obtener datos del usuario
                Usuario usuario = usuarioRepository.findById(compra.getId_usuario()).orElse(null); // Suponiendo que id_usuario es la relación con el usuario

                // Rellenar la fila con datos
                row.createCell(0).setCellValue(compra.getId_compra());
                row.createCell(1).setCellValue(compra.getId_usuario());

                if (usuario != null) {
                    row.createCell(2).setCellValue(usuario.getNumero_documento());
                    row.createCell(3).setCellValue(usuario.getNombre());
                    row.createCell(4).setCellValue(usuario.getPrimer_apellido());
                    row.createCell(5).setCellValue(usuario.getCorreo());
                    row.createCell(6).setCellValue(usuario.getTelefono());
                }

                row.createCell(7).setCellValue(compra.getDireccion_entrega());

                Cell fechaVentaCell = row.createCell(8);
                if (compra.getFecha_venta() != null) {
                    fechaVentaCell.setCellValue(compra.getFecha_venta());
                    fechaVentaCell.setCellStyle(dateStyle);
                }

                row.createCell(9).setCellValue(compra.getSubtotal());
                row.createCell(10).setCellValue(compra.getIgv());
                row.createCell(11).setCellValue(compra.getTotal());
            }

            // Ajustar el ancho de las columnas
            for (int i = 0; i < columnHeaders.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Escribir datos en un ByteArrayOutputStream
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            workbook.write(baos);
            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error al generar el reporte Excel", e);
        }
    }
}
