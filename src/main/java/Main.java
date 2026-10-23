public class Main {
    public static void main(String[] args) throws IOException {
        String data = Utils.lireFichier("src/texte.txt");
        FichierWord fw = new FichierWord("texte.docx");
        fw.setData(data);
        fw.writeFile();
    public static void main(String[] args) {
        System.out.println("cc");
    }
}
