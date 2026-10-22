import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Utils {

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

    // Lis un fichier dans un String
    public static String lireFichier(String fichier) throws FileNotFoundException {
        File f = new File(fichier);
        Scanner lecteur = new Scanner(f);
        StringBuilder s = new StringBuilder();
        while (lecteur.hasNext()) {
            s.append(lecteur.nextLine());
            s.append("\n");
        }
        return s.toString();
    }
}
