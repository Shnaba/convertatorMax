import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FichierWord implements Fichier {
    private String file;
    private String data;



    public FichierWord(String file) {
        this.file = file;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public void readFile() throws IOException {
        FileInputStream fis= new FileInputStream(new File(file).getAbsoluteFile());
        XWPFDocument document = new XWPFDocument(fis);
        XWPFWordExtractor extracteur = new XWPFWordExtractor(document);
        String texte = extracteur.getText();
//        System.out.println(texte);
        setData(texte);
        fis.close();
    }

    @Override
    public void writeFile() throws IOException {
        //try with resources pour fermer automatiquement les fichiers en cas d'exception
        try (XWPFDocument document = new XWPFDocument();
             FileOutputStream monFichier = new FileOutputStream(file)) {

            for (String ligne : data.split("\n")) {
                XWPFParagraph paragraph = document.createParagraph();
                paragraph.setAlignment(ParagraphAlignment.LEFT);

                XWPFRun run = paragraph.createRun();
                run.setText(ligne);
                run.setFontFamily("Arial");
                run.setFontSize(11);

            }

            document.write(monFichier);
            System.out.println("Le document " + file + " a été créé");
        }
    }


}

