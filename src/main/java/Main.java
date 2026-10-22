package fr.greta.fichier;

import fr.greta.stock.Produit;
import fr.greta.stock.ProduitAlimentaire;
import fr.greta.stock.ProduitElectronique;
import fr.greta.stock.Stock;

import java.io.IOException;
import java.time.LocalDate;

public class Main  {

    public static void main(String[] args)  {
        Produit p4=new ProduitElectronique("ELEC2","camera sony",800,5,36);
        Produit p1= new ProduitAlimentaire("AD23","beurre",4.5,23, LocalDate.of(2025,9,16));
        Produit p2= new ProduitAlimentaire("AB46","jambon",3.55,15, LocalDate.of(2025,10,16));
        Produit p3=new ProduitElectronique("ELEC1","carte son edirol",400.5,2,12);
        Stock s= new Stock();
        // ajout des produits
        s.ajouterProduit(p1);
        s.ajouterProduit(p2);
        s.ajouterProduit(p3);
        s.ajouterProduit(p4);
        //affichage
        s.afficher();

        try {
            Fichier tab[]= new Fichier[5];
            Fichier f1 = new CsvFile("fichier.csv",s.getListeProduits());
            Fichier f2 = new PDFfile("fichier.pdf",s.getListeProduits());
            Fichier f3 = new WordFile("fichier.docx");
            Fichier f4 = new ExcelFile("fichier.xlsx");
            Fichier f5 = new JSONFile("fichier.json");
             tab[0]=f1;
            tab[1]=f2;
            tab[2]=f3;
            tab[3]=f4;
            tab[4]=f5;

            for(Fichier f : tab) {
                f.writeFile();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        //Fichier f2 = new PDFfile("fichier.pdf");
    }
}
