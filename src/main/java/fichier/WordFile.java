package fichier;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class WordFile implements Fichier {
    private String file;

    public WordFile(String file) {
        this.file = file;
    }


    @Override
    public void readFile() throws IOException {
        FileInputStream fis= new FileInputStream(new File(file).getAbsoluteFile());
        XWPFDocument document = new XWPFDocument(fis);
        XWPFWordExtractor extracteur = new XWPFWordExtractor(document);
        String texte = extracteur.getText();
        System.out.println(texte);
        fis.close();

    }

    @Override
    public void writeFile() throws IOException {
        try {
// ducument word vide
        XWPFDocument document = new XWPFDocument();
        // creer le doc dans le chemin spécifique
        // chemin relatif : dossierWord/doc.docx
        //chemin absolu : c://users/cda/doc.docx
        FileOutputStream monFile= new FileOutputStream(new File(file));


        //créer un paragraphe
        XWPFParagraph paragraph= document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run= paragraph.createRun();
        //ajout du titre - font-color- gras
        run.setText("Faire du Word avec Java");
        run.setFontFamily("Tahoma");
        run.setColor("5DADE2");
        run.setBold(true);
        run.setFontSize(20);
        //retour à la ligne
        run.addBreak();
        run.addBreak();

        //insertion image
        String imgFile= "src/main/resources/logo-Android-Histoire.jpg";
        FileInputStream fi=new FileInputStream(imgFile);

            run.addPicture(fi,XWPFDocument.PICTURE_TYPE_JPEG,imgFile, Units.toEMU(200),Units.toEMU(200));
            run.addBreak();
            //TODO aligner le paragraphe à gauche
        XWPFRun run2= paragraph.createRun();
        run2.setText("Le 14 mars 2004 , il récite les 22514 décimales du nombre PI"+
                        "en 5heures 9 minutes et 24 secondes, établissant un nouveau record"+
                " Européen.");
        run2.addBreak();
            run2.setText("Il est atteint du syndrome d'Asperger.");
            run2.addBreak();
            run2.setText("IL est polyglotte et a appris l'islandais en 3 semaines.");
            run2.addBreak();
            run2.setText("Qui suis-je?");



        document.write(monFile);
        monFile.close();
        System.out.println("Le document "+ file + " a été créé avec succes");
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }
        /**
         * 1- createRun()
         * Concatène un nouveau paragraphe au précédent
         *
         * 2-getAlignement()
         * Retourne l'alignement du paragraphe lequel va être appliqué au texte suivant de ce paragraphe.
         *
         * 3-setAlignment(ParagraphAlignment align)
         * Spécifie l'alignement du paragraphe lequel va etre appliqué au texte suivant de ce paragraphe.
         *
         * 4-setBorderBottom(Borders border)
         * Spécifie la bordure qui doit être affichée ci-dessous.
         *
         * 5-setBorderLeft(Borders border)
         * Spécifie la bordure qui doit être affichée à gauche.
         *
         * 6-setBorderRight(Borders border)
         * Spécifie la bordure qui doit être affichée à droite.
         *
         * 7-setBorderTop(Borders border)
         * Spécifie la bordure qui doit être affichée ci-dessus.
         */
    }
}
