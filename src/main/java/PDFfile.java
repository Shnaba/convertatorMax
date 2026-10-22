package fr.greta.fichier;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import fr.greta.stock.Produit;
import fr.greta.stock.Util;

import java.io.IOException;
import java.util.List;

public class PDFfile implements Fichier {

    private String file;
    private List<Produit> listeProduits;

    public PDFfile(String file, List<Produit> l) {
        this.file = file;
        this.listeProduits=l;
    }

    @Override
    public void readFile() throws IOException {
        PdfReader pr = new PdfReader(file);
        PdfDocument doc = new PdfDocument(pr);
        int num = doc.getNumberOfPages();

        for (int i = 1; i <= num; i++) {
            String str = PdfTextExtractor.getTextFromPage(doc.getPage(i));
            System.out.println(str);
        }
        doc.close();
    }

    @Override
    public void writeFile() throws IOException {
        //gestionnaire de l'ecriture dans le fichier pdf
        PdfWriter writer = new PdfWriter(file);
        //creation d'un document avec plusieurs page
        PdfDocument pdfDoc = new PdfDocument(writer);
        //creation du contenu de la page du document
        Document doc = new Document(pdfDoc);

        PdfFont myFont = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
        Paragraph p1 = new Paragraph();
        p1.add("Rapport du Stock du "+ Util.dateJour());
        p1.setTextAlignment(TextAlignment.CENTER);
        p1.setFont(myFont);
        p1.setFontSize(18);
        doc.add(p1);
        //saut de ligne
        doc.add(new Paragraph("\n"));

        //création d'un tableau
        float [] pointColumnWidths = {80F, 150F, 80F,80F,100F};
        Table table = new Table(pointColumnWidths);
        //table.setWidth(400);
        table.addCell("Code");
        table.addCell("Nom");
        table.addCell("Prix");
        table.addCell("Quantité");
        table.addCell("Montant");
        double total=0;
        double montant=0;
        for(Produit p : this.listeProduits) {
            montant =0;
            table.addCell(p.getCode());
            table.addCell(p.getNom());
            table.addCell(String.valueOf(p.getPrix())+ " €");
            table.addCell(String.valueOf(p.getQuantiteStock()));
            montant= p.getQuantiteStock()*p.getPrix();
            table.addCell(String.valueOf((montant))+ " €");
            //calcul du total
            total+=montant;
        }

            doc.add(table);
        float [] pointColumnTotal = {490F};
        Table tableTotal = new Table(pointColumnTotal);
        tableTotal.addCell("Total des commandes: "+total+" €");
        doc.add(tableTotal);
        doc.close();
    }
}
