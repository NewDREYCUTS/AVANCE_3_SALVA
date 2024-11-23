package com.web.restaurante.proyecto.reportes;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;

import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.web.restaurante.proyecto.clases.BoletaCompra;
import com.web.restaurante.proyecto.clases.CarritoCompras;
import com.web.restaurante.proyecto.repositorio.BoletaCompraRepository;
import com.web.restaurante.proyecto.repositorio.CarritoCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.apache.poi.poifs.macros.Module.ModuleType.Document;

@Service
public class ReporteBoletaPdfService {

    @Autowired
    private BoletaCompraRepository boletaCompraRepository;

    @Autowired
    private CarritoCompraRepository carritoCompraRepository;

    public byte[] generarBoletaPDF(String id_compra) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        // Establecer márgenes
        document.setMargins(20, 20, 20, 20);

        // Obtener boleta de compra
        Optional<BoletaCompra> boletaOptional = boletaCompraRepository.findById(id_compra);
        if (boletaOptional.isEmpty()) {
            throw new IllegalArgumentException("Boleta no encontrada");
        }
        BoletaCompra boleta = boletaOptional.get();

        // Nombre de la empresa
        Paragraph empresaNombre = new Paragraph("BOLETA DE COMPRA")
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(20)
                .setBold();
        document.add(empresaNombre);

        // Información de la boleta (sin fecha)
        Table infoTable = new Table(UnitValue.createPercentArray(2)).useAllAvailableWidth();
        infoTable.addCell(new Cell().add(new Paragraph("ID Compra:").setBold()));
        infoTable.addCell(new Cell().add(new Paragraph(id_compra)));
        infoTable.addCell(new Cell().add(new Paragraph("Dirección de Entrega:").setBold()));
        infoTable.addCell(new Cell().add(new Paragraph(boleta.getDireccion_entrega()))); // Agregar dirección de entrega
        document.add(infoTable);

        // Separador
        document.add(new Paragraph("\n"));

        // Tabla de productos
        Table productTable = new Table(UnitValue.createPercentArray(new float[]{2, 3, 1, 1, 2})).useAllAvailableWidth(); // Ajustamos las columnas
        productTable.addHeaderCell(new Cell().add(new Paragraph("Producto").setBold()).setBackgroundColor(ColorConstants.LIGHT_GRAY));
        productTable.addHeaderCell(new Cell().add(new Paragraph("Descripción").setBold()).setBackgroundColor(ColorConstants.LIGHT_GRAY));
        productTable.addHeaderCell(new Cell().add(new Paragraph("Cantidad").setBold()).setBackgroundColor(ColorConstants.LIGHT_GRAY));
        productTable.addHeaderCell(new Cell().add(new Paragraph("Precio Unitario").setBold()).setBackgroundColor(ColorConstants.LIGHT_GRAY));
        productTable.addHeaderCell(new Cell().add(new Paragraph("Imagen").setBold()).setBackgroundColor(ColorConstants.LIGHT_GRAY)); // Nueva columna para la imagen

        // Agregar productos comprados
        List<CarritoCompras> productosComprados = carritoCompraRepository.misCompras(id_compra);
        for (CarritoCompras producto : productosComprados) {
            productTable.addCell(new Cell().add(new Paragraph(producto.getNombre_producto())));
            productTable.addCell(new Cell().add(new Paragraph(producto.getDescripcion())));
            productTable.addCell(new Cell().add(new Paragraph(String.valueOf(producto.getCantidad_carrito()))));
            productTable.addCell(new Cell().add(new Paragraph(String.valueOf(producto.getPrecio_carrito()))));

            // Agregar imagen del producto (si está disponible)
            String imagenProducto = producto.getImg(); // Asegúrate de que el objeto `CarritoCompras` tenga un campo para la imagen.
            try {
                ImageData imageData = ImageDataFactory.create(imagenProducto);
                Image image = new Image(imageData).scaleToFit(50, 50); // Ajusta el tamaño de la imagen
                productTable.addCell(new Cell().add(image));
            } catch (Exception e) {
                productTable.addCell(new Cell().add(new Paragraph("Imagen no disponible")));
            }
        }

        // Añadir la tabla de productos
        document.add(productTable);

        // Resumen de la boleta (sin subtotal repetido)
        document.add(new Paragraph("\n"));
        Table resumenTable = new Table(UnitValue.createPercentArray(2)).useAllAvailableWidth();
        resumenTable.addCell(new Cell().add(new Paragraph("Subtotal:").setBold()));
        resumenTable.addCell(new Cell().add(new Paragraph(String.valueOf(boleta.getSubtotal()))));  // Aquí solo se muestra una vez
        resumenTable.addCell(new Cell().add(new Paragraph("IGV:").setBold()));
        resumenTable.addCell(new Cell().add(new Paragraph(String.valueOf(boleta.getIgv()))));
        resumenTable.addCell(new Cell().add(new Paragraph("Total:").setBold()));
        resumenTable.addCell(new Cell().add(new Paragraph(String.valueOf(boleta.getTotal()))));
        document.add(resumenTable);

        // Cerrar documento
        document.close();

        return baos.toByteArray();
    }
}
