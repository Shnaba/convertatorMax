import exceptions.ExcelExecption;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FichierExcel implements Fichier {

    private String fichier;
    private Object[][] data;

    public FichierExcel(String fichier) {
        this.fichier = fichier;
    }

    @Override
    public void readFile() throws IOException {
        // TODO
    }

    @Override
    public void writeFile() throws IOException, ExcelExecption {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet1 = workbook.createSheet("Java");

        XSSFCellStyle style = workbook.createCellStyle();

        int iRow = 0;
        for (Object[] livre : data) {

            Row row = sheet1.createRow(++iRow);
            int iCol = 0;

            for (Object champ : livre) {

                Cell cell = row.createCell(++iCol);

                cell.setCellStyle(style);

                if (champ instanceof String) {
                    cell.setCellValue((String) champ);
                } else if (champ instanceof Integer) {
                    cell.setCellValue((Integer) champ);
                } else if (champ instanceof Float) {
                    cell.setCellValue((Float) champ);
                }
            }
        }
        FileOutputStream out = new FileOutputStream(new File(this.fichier));
        workbook.write(out);
        out.close();
    }

    public String getFichier() {
        return fichier;
    }

    public void setFichier(String fichier) {
        this.fichier = fichier;
    }

    public Object[][] getData() {
        return data;
    }

    public void setData(Object[][] data) {
        this.data = data;
    }

}
