package fichier;
import fr.greta.stock.Produit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * classe permettant de générer un fichier au format csv
 */
public class CsvFile implements Fichier {
    private String file;
    private List<Produit> listeProduits;

    public CsvFile(String file,List<Produit> l)
    {
        this.file = file;
        this.listeProduits=l;
    }

    /**
     * méthode permettant de lire un fichier
     * @throws IOException
     */
    @Override
    public void readFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String ligne = null;
        String[] data = new String[3];
        while ((ligne = br.readLine()) != null) {
            //retourne loa ligne du fichier
            // et stocke chaque occurence délimité par un ,
            // dans une case du tableau
            data = ligne.split(",");

            for (String val : data) {
                System.out.print(val +" ");
            }
            System.out.println();
        }

    }

    @Override
    public void writeFile() throws IOException {
        final String DELIMITER = ",";
        final String SEPARATOR = "\n";
        // entete du fichier csv
        final String HEADER = "Code,Type,Nom,Prix,Quantité,Info";
        FileWriter fichier = new FileWriter(file);
        //ajout de l'entete
        fichier.append(HEADER);
        //ajout d'une nouvelle ligne
        fichier.append(SEPARATOR);
        for(Produit p : this.listeProduits){
            fichier.append(p.getCode());
           fichier.append(DELIMITER);
            fichier.append(p.getNom());
            fichier.append(DELIMITER);
            fichier.append(String.valueOf(p.getPrix()));
            fichier.append(DELIMITER);
            fichier.append(String.valueOf(p.getQuantiteStock()));
            fichier.append(SEPARATOR);
        }

        //fermer mon fichier

        fichier.close();


    }
}