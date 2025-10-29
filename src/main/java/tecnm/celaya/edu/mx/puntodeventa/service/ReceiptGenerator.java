package tecnm.celaya.edu.mx.puntodeventa.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;
import tecnm.celaya.edu.mx.puntodeventa.model.Customer;
import tecnm.celaya.edu.mx.puntodeventa.model.Sale;
import tecnm.celaya.edu.mx.puntodeventa.model.SaleDetail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class ReceiptGenerator {

    public static void generateReceipt(Sale sale, Customer customer, List<SaleDetail> details, String filePath) throws FileNotFoundException {
        try (PdfWriter writer = new PdfWriter(filePath);
             PdfDocument pdf = new PdfDocument(writer);
             Document document = new Document(pdf)) {

            document.add(new Paragraph("Recibo de Venta").setBold().setFontSize(20));
            document.add(new Paragraph("Fecha: " + sale.getSaleDate().toLocalDate()));
            document.add(new Paragraph("Cliente: " + customer.getFirstName() + " " + customer.getLastName()));
            document.add(new Paragraph("\n"));

            Table table = new Table(UnitValue.createPercentArray(new float[]{4, 1, 2, 2}));
            table.setWidth(UnitValue.createPercentValue(100));

            table.addHeaderCell("Producto");
            table.addHeaderCell("Cantidad");
            table.addHeaderCell("Precio Unitario");
            table.addHeaderCell("Subtotal");

            for (SaleDetail detail : details) {
                table.addCell(detail.getProductName());
                table.addCell(String.valueOf(detail.getQuantity()));
                table.addCell(String.format("$%.2f", detail.getUnitPrice()));
                table.addCell(String.format("$%.2f", detail.getSubtotal()));
            }

            document.add(table);

            document.add(new Paragraph("\n"));
            document.add(new Paragraph("Total: " + String.format("$%.2f", sale.getTotal())).setBold());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
