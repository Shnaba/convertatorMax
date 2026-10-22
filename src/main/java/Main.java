import exceptions.ExcelExecption;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Starting...");

        FichierExcel f = new FichierExcel("text.xlsx");

        String s = Utils.lireFichier("texte.txt");
        f.setData(Utils.stringTo2DTab(s));
        f.writeFile();




    }
}
