import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Utils {

    public static boolean fichierExiste(String fichier) {
        File f = new File(fichier);
        return f.canRead();
    }

    public static String getExtension(String fichier) {
        String[] f = fichier.trim().split("\\.");
        return f[f.length - 1];
    }

    public static String controleExtension(String fichier) {
        List<String> extensionsValides = new ArrayList<>(Arrays.asList("txt", "docx", "pdf", "xlsx"));

        String ext = getExtension(fichier);
        if (extensionsValides.contains(ext))
            return ext;
        return "";
    }

    // Transforme un string en un tableau en 2D (pas de gestion d'erreur)
    public static Object[][] stringTo2DTab(String s) {
        // Un élément du tableau "lignes" par ligne de s
        String[] lignes = s.lines().toArray(String[]::new);
        // Compte le nombre de lignes et colonnes du future tableau
        int nRows = lignes.length;
        int nColums = lignes[0].trim().split("\\s+").length;
        // Créer le tableau
        Object[][] tab = new Object[nRows][nColums];
        // Remplie le tableau avec le bon type de donnée
        for (int l=0; l<nRows; l++) {
            String[] ligne = lignes[l].trim().split("\\s+");
            for (int c=0; c<nColums; c++) {
                String elem = ligne[c];
                if (elem.matches("[+-]?\\d+"))
                    tab[l][c] = Integer.parseInt(elem);
                else if (elem.matches("[+-]?\\d+\\.\\d+"))
                    tab[l][c] = Float.parseFloat(elem);
                else
                    tab[l][c] = elem;
            }
        }
        return tab;
    }

}
