package com.electrowaveselectronics.inventorymanagement.service;

import com.electrowaveselectronics.inventorymanagement.entity.Customer;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class BillPdfService {

    private Logger logger = LoggerFactory.getLogger(BillPdfService.class);
    public ByteArrayInputStream createPdf(){
        logger.info("Create PDF Started");


        // Sample data for demonstration
        String sellerName = "Vijay kumar";
        String godownAddress = "Industrial Area Phase 1, Panchkula, Haryana 134113";
        String invoiceNumber = "INV-001";
        String invoiceDate = "2024-01-17";
        String totalAmount = "$100.00";
//        String title = "Bn gya bill";
//        String contenet = "product List";
//        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD,25);

        ByteArrayOutputStream out = new ByteArrayOutputStream();


//        Document document = new Document();
//        PdfWriter.getInstance(document,out);
//        document.open();
//
//        Paragraph titlePara = new Paragraph(title,titleFont);
//        titlePara.setAlignment(Element.ALIGN_CENTER);
//        document.add(titlePara);
//
//        Font paraFont = FontFactory.getFont(FontFactory.HELVETICA,18);
//        Paragraph paragraph = new Paragraph(contenet);
//        document.add(paragraph);
//
//        document.close();
        try {
            Resource resource = new ClassPathResource("assets/pyramidLogo.png");
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, out);
            PdfWriter.getInstance(document, out);
            document.open();

            // Title
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 25);
            PdfPCell titleCell = new PdfPCell(new Paragraph("TAX Invoice", titleFont));
            titleCell.setBorder(Rectangle.NO_BORDER);
            titleCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            // Image
            Image img = Image.getInstance("src/main/resources/assets/pyramidLogo.png"); // Replace "path_to_your_image.jpg" with the actual path to your image file
            img.scaleToFit(100, 100); // Adjust the image size as needed
            PdfPCell imageCell = new PdfPCell(img);
            imageCell.setBorder(Rectangle.NO_BORDER);


            PdfPTable titleImageTable = new PdfPTable(2);
            titleImageTable.setWidthPercentage(100);
            titleImageTable.setWidths(new float[]{1, 1});
            titleImageTable.addCell(imageCell);
            titleImageTable.addCell(titleCell);


            titleImageTable.setSpacingAfter(20f);
            document.add(titleImageTable);


            // Border for the bill section
            Rectangle border = new Rectangle(document.left()-10, document.top(), document.right()+10, document.bottom() + 100);
            border.setBorder(Rectangle.BOX);
            border.setBorderColor(Color.BLACK);
            border.setBorderWidth(1f);
            document.add(border);

            // Seller Details
            Font sellerFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
            Paragraph sellerPara = new Paragraph();
            sellerPara.setFont(sellerFont);
            sellerPara.add("Sold By"+ "\n");
            sellerPara.add( sellerName + "\n");
            sellerPara.add(godownAddress + "\n");
            sellerPara.add("Order Id : "+invoiceNumber + "\n");
            sellerPara.add("Invoice Date: " + invoiceDate + "\n");
            sellerPara.setSpacingAfter(10f);
            document.add(sellerPara);

            // Customer Details
            Font customerFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
            Paragraph customerPara = new Paragraph();
            customerPara.setFont(customerFont);
            customerPara.add("Invoice To"+ "\n");
            customerPara.add("Name : "+ sellerName + "\n");
            customerPara.add("Address : "+ godownAddress + "\n");

            customerPara.setSpacingAfter(10f);
            document.add(customerPara);

            // Invoice Items (Sample)
            Font itemFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            float[] columnWidths = {2f, 2f, 2f};
            table.setWidths(columnWidths);

            PdfPCell cell = new PdfPCell(new Paragraph("Product"));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(Color.LIGHT_GRAY);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("Quantity"));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(Color.LIGHT_GRAY);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("Price"));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(Color.LIGHT_GRAY);
            table.addCell(cell);

            // Sample Invoice Items
            table.addCell(new Paragraph("Product A", itemFont));
            table.addCell(new Paragraph("2", itemFont));
            table.addCell(new Paragraph("$50.00", itemFont));

            table.addCell(new Paragraph("Product B", itemFont));
            table.addCell(new Paragraph("1", itemFont));
            table.addCell(new Paragraph("$50.00", itemFont));

            document.add(table);

            // Total Amount
            Font totalFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
            Paragraph totalPara = new Paragraph("Total Amount: " + totalAmount, totalFont);
            totalPara.setAlignment(Element.ALIGN_RIGHT);
            document.add(totalPara);

            document.close();
        } catch (DocumentException e) {
            logger.error("Error occurred: {}", e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
