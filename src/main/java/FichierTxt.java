import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FichierTxt implements Fichier {

    private String fichier;
    private String data;

    public FichierTxt(String fichier) {
        this.fichier = fichier;
    }

    @Override
    public void readFile() throws IOException {
        File f = new File(fichier);
        Scanner lecteur = new Scanner(f);
        StringBuilder s = new StringBuilder();
        while (lecteur.hasNext()) {
            s.append(lecteur.nextLine());
            s.append("\n");
        }
        setData(s.toString());
    }

    @Override
    public void writeFile() throws IOException {
        FileWriter fw = new FileWriter(fichier);
        fw.write(data);
        fw.close();
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
