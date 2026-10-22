import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FichierWord word = new FichierWord("texte.docx");
        word.convertirDepuisTexte("src/texte.txt");
    }
}
