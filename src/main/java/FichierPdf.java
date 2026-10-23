import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.IOException;

public class FichierPdf implements Fichier {

    private String fichier;
    private String data;

    public FichierPdf(String fichier) {
        this.fichier = fichier;
    }

    @Override
    public void readFile() throws IOException {
        PdfReader pr = new PdfReader(this.fichier);
        PdfDocument doc = new PdfDocument(pr);
        int num = doc.getNumberOfPages();
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= num; i++) {
            String str = PdfTextExtractor.getTextFromPage(doc.getPage(i));
            sb.append(str);
        }
        doc.close();
        setData(sb.toString());
    }

    @Override
    public void writeFile() throws IOException {
        //gestionnaire de l'ecriture dans le fichier pdf
        PdfWriter writer = new PdfWriter(this.fichier);
        //creation d'un document avec plusieurs page
        PdfDocument pdfDoc = new PdfDocument(writer);
        //creation du contenu de la page du document
        Document doc = new Document(pdfDoc);

        PdfFont myFont = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        Paragraph p = new Paragraph();
        p.add(this.data);
        doc.add(p);
        doc.close();
    }

    public String getFichier() {
        return fichier;
    }

    public void setFichier(String fichier) {
        this.fichier = fichier;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
