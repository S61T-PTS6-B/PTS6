/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdf;
 
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfString;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import model.Cordon;
import model.Invoice;
 
/**
 *
 * @author Max
 */
public class PdfBuilder {
 
    /** The resulting PDF. */
    public static String DEST = "C://temp/";
   
    public static final String FONT = "C://temp/arial.ttf";
 
    /**
     * Creates an accessible PDF with images and text.
     * @param dest  the path to the resulting PDF
     * @throws IOException
     * @throws DocumentException
     */
    public String createPdf(Invoice invoice) throws IOException, DocumentException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        DEST += invoice.getCar().getId() + "month" + sdf.format(invoice.getSeriesOfLocationsOnRoad().get(0).getLocations().get(0).getDate()) + ".pdf";
       
        File file = new File(DEST);
        file.getParentFile().mkdirs();
       
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(DEST));
        writer.setPdfVersion(PdfWriter.VERSION_1_7);
        //TAGGED PDF
        //Make document tagged
        writer.setTagged();
        //===============
        //PDF/UA
        //Set document metadata
        writer.setViewerPreferences(PdfWriter.DisplayDocTitle);
        document.addLanguage("en-US");
        document.addTitle("Factuur voor auto " + invoice.getCar().getLicensePlate());
        writer.createXmpMetadata();
        //=====================
        document.open();
 
        Font font = FontFactory.getFont(FONT, BaseFont.WINANSI, BaseFont.EMBEDDED, 20);
       
        double amountPaidForDistance = invoice.getTotalAmount();
        double amountPaidForCordons = 0;
       
        for (Cordon c : invoice.getCordonOccurrences()) {
            amountPaidForDistance -= c.getAmount();
            amountPaidForCordons += c.getAmount();
        }
 
        Paragraph p = new Paragraph("\n", font);
        p.add(new Chunk("Aantal gereden kilometers:"));
        p.add(new Chunk("\n"));
        p.add(new Chunk(String.valueOf(invoice.getTotalDistance()) + " kilometer"));
        p.add(new Chunk("\n"));
        p.add(new Chunk("Met een gemiddeld tarief van " + (amountPaidForDistance / invoice.getTotalDistance() * 100) + " eurocent per kilometer"));
        p.add(new Chunk("\n"));
        p.add(new Chunk("Bedrag dat betaald dient te worden over de kilometers: "));
        p.add(new Chunk("\n"));
        p.add(new Chunk(amountPaidForDistance + " euro"));
        document.add(p);
       
        p = new Paragraph("\n\n", font);
        p.add(new Chunk(invoice.cordonOccurrencesString()));
        p.add(new Chunk("\n"));
        p.add(new Chunk("Bedrag dat betaald dient te worden over de cordons: "));
        p.add(new Chunk("\n"));
        p.add(new Chunk(amountPaidForCordons + " euro"));
        document.add(p);
       
        p = new Paragraph("\n\n", font);
        p.add(new Chunk("Totaal bedrag dat betaald dient te worden: "));
        p.add(new Chunk("\n"));
        p.add(new Chunk(String.valueOf(invoice.getTotalAmount()) + " euro"));
        document.add(p);
        document.close();
 
        return DEST;
    }
 
}