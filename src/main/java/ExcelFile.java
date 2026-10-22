package fr.greta.fichier;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelFile implements Fichier {

    private String file;

    public ExcelFile(String file) {
        this.file = file;
    }

    @Override
    public void readFile() throws IOException {

    }

    @Override
    public void writeFile() throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        //creer une feuille de calcul

        XSSFSheet sheet1 = workbook.createSheet("Java");
        XSSFSheet sheet2 = workbook.createSheet("PHP");

        Object[][] data = {
                {"Livre", "Auteur", "Prix"},
                {"Base de java", "Jacques Chirac", 79},
                {"Niveau avancé de Java", "Nicolas Sarkozy", 5},
                {"NIveau expert de java", "Bernadette Chirac", 12},
                {"NIveau pro de Java", "Francois Hollande", 8},
        };
        XSSFCellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(new XSSFColor(new java.awt.Color(127, 0, 128), new DefaultIndexedColorMap()));

        int rowNb = 0;
        for (Object[] livre : data) {

            Row row = sheet1.createRow(++rowNb);
            int colonneNb = 0;

            for (Object champ : livre) {

                Cell cell = row.createCell(++colonneNb);

                cell.setCellStyle(style);

                if (champ instanceof String) {
                    cell.setCellValue((String) champ);
                } else if (champ instanceof Integer) {
                    cell.setCellValue((Integer) champ);
                }
            }
        }
        FileOutputStream out = new FileOutputStream(new File(file));
        workbook.write(out);
        out.close();


    }


}

