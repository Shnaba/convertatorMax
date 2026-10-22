import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FichierWord implements Fichier {
    private String file;

    public FichierWord(String file) {
        this.file = file;
    }


    @Override
    public void readFile() throws IOException {

    }

    @Override
    public void writeFile() throws IOException {
        try {
            XWPFDocument document = new XWPFDocument();

            FileOutputStream monFichier = new FileOutputStream(new File(file));


            //créer un paragraphe
            XWPFParagraph paragraph = document.createParagraph();
            paragraph.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun run = paragraph.createRun();
            //ajout du titre - font-color- gras
            run.setText("15 OCTOBRE 2026");
            run.setFontFamily("Arial");
            run.setColor("727B89");
            run.setBold(true);
            run.setFontSize(10);
            //retour à la ligne
            run.addBreak();

            XWPFRun titre = paragraph.createRun();
            titre.setText("Mise à jour de Counter-Strike 2");
            titre.setBold(true);
            titre.setFontSize(20);
            titre.addBreak();

            XWPFRun run2 = paragraph.createRun();
            run2.setText("[INTERFACE]");
            run2.addBreak();
            run2.setText("Améliorations apportées au lecteur de rediffusions.");
            run2.addBreak();
            run2.setText("  - Ajout d'une barre de progression pour les téléchargements de rediffusions.");
            run2.addBreak();
            run2.setText("  - Les commandes de la caméra utilisent désormais un système de cliquer-glisser.");
            run2.addBreak();
            run2.setText("  - La caméra libre et la caméra de poursuite sont désormais utilisables quand la rediffusion est en pause.");
            run2.addBreak();
            run2.setText("  - Correction de quelques problèmes mineurs avec la recherche au sein d'un fichier de rediffusion.");
            run2.addBreak();

            XWPFRun run3 = paragraph.createRun();
            run3.addBreak();
            run3.setText("[DIVERS]");
            run3.addBreak();
            run3.setText("Les fichiers de sauvegarde de la manche nécessitent désormais le paramètre de démarrage du serveur de jeu « -allow_round_backup_files ».");
            run3.addBreak();
            run3.setText("Mise à jour des animations de rechargement de l'AUG en position debout et accroupie pour mieux correspondre à la nouvelle durée de rechargement.");
            run3.addBreak();
            run3.setText("Ajout d'une liste explicite des commandes radio autorisées.");
            run3.addBreak();
            run3.setText("Ajout du matériau « toolsskybox_occluder » pour les outils du Workshop CS2.");
            run3.addBreak();
            run3.setText("Améliorations de la stabilité du moteur de rendu Vulkan.");

            document.write(monFichier);
            monFichier.close();
            System.out.println("Le document " + file + " a été créé avec succes");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    public void convertirDepuisTexte(String fichierTexte) throws IOException {
        String contenu = Utils.lireFichier(fichierTexte);

        try (XWPFDocument document = new XWPFDocument();
             FileOutputStream monFichier = new FileOutputStream(file)) {

            for (String ligne : contenu.split("\n")) {
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

//A mettre dans le main pour tester :
//        System.out.println("cc");
//        Fichier test = new FichierWord("patchNote.docx");
//        test.writeFile();
