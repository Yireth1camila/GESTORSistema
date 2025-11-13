/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplicacion;


import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;

public class UtilPDF {

    public static String generarBoleta(Venta venta) {
        return generarBoleta(venta, "PDF_Ventas");
    }
//generaPdf
    public static String generarBoleta(Venta venta, String pdf_Ventas) {
        String archivo = "Boleta_" + venta.getNombreCliente() + "_" + venta.getId() + ".pdf";
        try {
            Document doc = new Document(PageSize.A6);
            PdfWriter.getInstance(doc, new FileOutputStream(archivo));//Pdf
            doc.open();
            DecimalFormat df = new DecimalFormat("0.00");
            Font titulo = new Font(Font.HELVETICA, 14, Font.BOLD);
            doc.add(new Paragraph(venta.getEvento().getNombre(), titulo));
            Font normal = new Font(Font.HELVETICA, 10, Font.NORMAL);
            doc.add(new Paragraph("Artista: " + venta.getEvento().getArtista(), normal));
            doc.add(new Paragraph("Cliente: " + venta.getNombreCliente(), normal));
            doc.add(new Paragraph("Nivel: " + venta.getEvento().getNivel(), normal));
            String colorNombre = venta.getEvento().getNivel().getColor();
            Color nivelColor = Color.BLACK;
            switch (colorNombre.toLowerCase()) {
                case "dorado": nivelColor = new Color(212,175,55); break;
                case "plata": nivelColor = Color.LIGHT_GRAY; break;
                case "azul": nivelColor = Color.BLUE; break;
            }
            Font colorFont = new Font(Font.HELVETICA, 10, Font.NORMAL, nivelColor);
            doc.add(new Paragraph("Color: " + colorNombre, colorFont));
            doc.add(new Paragraph("Precio Total: $" + df.format(venta.getPrecioTotal()), normal));
            doc.add(new Paragraph("Vendedor: " + venta.getVendedor().getNombre(), normal));
            doc.add(new Paragraph("Fecha: " + venta.getFechaHora(), normal));
            doc.add(new Paragraph("Código Venta: " + venta.getId(), normal));
            // QR
            BufferedImage qrImage = generarQR("VentaID:" + venta.getId() + "|Cliente:" + venta.getNombreCliente(), 100, 100);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(qrImage, "PNG", baos);
            Image qr = Image.getInstance(baos.toByteArray());
            qr.setAlignment(Element.ALIGN_CENTER);
            doc.add(qr);
            doc.close();
            return archivo;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static BufferedImage generarQR(String texto, int ancho, int alto) throws Exception {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(texto, BarcodeFormat.QR_CODE, ancho, alto);
        BufferedImage imagen = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < ancho; x++) {
            for (int y = 0; y < alto; y++) {
                int color = bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF;
                imagen.setRGB(x, y, color);
            }
        }
        return imagen;
    }

    static void generarResumen(Venta v) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    static void generarRegistroVentas(java.util.List<Venta> ventas, String pdf_RegistroVentasRegistro_Ventaspdf) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
