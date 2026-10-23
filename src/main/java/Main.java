import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        String fichierEntree;
        String extEntree = "";
        String fichierSortie;
        String extSortie = "";

        System.out.println("Saisir le fichier à convertir :");
        fichierEntree = scanner.nextLine();
        extEntree = Utils.controleExtension(fichierEntree);

        while (!Utils.fichierExiste(fichierEntree) && extEntree.matches("")) {
            System.out.println("Saisie invalide, recommencer :");
            fichierEntree = scanner.nextLine();
            extEntree = Utils.controleExtension(fichierEntree);
        }

        System.out.println("Saisir le fichier destination :");
        fichierSortie = scanner.nextLine();
        extSortie = Utils.controleExtension(fichierSortie);

        while (extSortie.matches("") && !extEntree.matches(extSortie)) {
            System.out.println("Saisie invalide, recommencer :");
            fichierSortie = scanner.nextLine();
            extSortie = Utils.controleExtension(fichierSortie);
        }

        String temp = "";

        switch (extEntree) {
            case "pdf":
                FichierPdf fichierEntreePdf = new FichierPdf(fichierEntree);
                fichierEntreePdf.readFile();
                temp = fichierEntreePdf.getData();
                break;
            case "docx":
                FichierWord fichierEntreeWord = new FichierWord(fichierEntree);
                fichierEntreeWord.readFile();
                temp = fichierEntreeWord.getData();
                break;
            case "txt":
                FichierTxt fichierEntreeTxt = new FichierTxt(fichierEntree);
                fichierEntreeTxt.readFile();
                temp = fichierEntreeTxt.getData();
                break;
            default:
                System.out.println("ERREUR");
                break;
        }

        switch (extSortie) {
            case "pdf":
                FichierPdf fichierSortiePdf = new FichierPdf(fichierSortie);
                fichierSortiePdf.setData(temp);
                fichierSortiePdf.writeFile();
                break;
            case "docx":
                FichierWord fichierSortieWord = new FichierWord(fichierSortie);
                fichierSortieWord.setData(temp);
                fichierSortieWord.writeFile();
                break;
            case "txt":
                FichierTxt fichierSortieTxt = new FichierTxt(fichierSortie);
                fichierSortieTxt.setData(temp);
                fichierSortieTxt.writeFile();
                break;
            case "xlsx":
                FichierExcel fichierSortieExcel = new FichierExcel(fichierSortie);
                Object[][] tempTab = Utils.stringTo2DTab(temp);
                fichierSortieExcel.setData(tempTab);
                fichierSortieExcel.writeFile();
                break;
            default:
                System.out.println("ERREUR 2");
                break;
        }

        System.out.println("Terminé !");


    }
}
