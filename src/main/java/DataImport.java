
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class DataImport {

    List<Long> numberList = new ArrayList<>();

    public void dataImport() {
        try (InputStream inputFile = getClass().getClassLoader().getResourceAsStream("InputFiles/vzorek_dat - kopie.xlsx")) {
            if (inputFile != null) {
                XSSFWorkbook wb = new XSSFWorkbook(inputFile);
                XSSFSheet sheet = wb.getSheetAt(0);
                XSSFRow firstRow = sheet.getRow(0);
                DataFormatter formatter = new DataFormatter();
                long foundCell = 0;
                String data = null;

                for (Row row : sheet) {
                    for (Cell cell : row) {
                        if (firstRow != row && !isRowEmpty(row) ) {
                            switch (cell.getCellType()) {
                                    case STRING -> data = formatter.formatCellValue(cell).trim();
                                    case NUMERIC -> data = formatter.formatCellValue(cell).trim();
                                    case BLANK -> data = formatter.formatCellValue(cell).trim();
                            }
                            foundCell = Long.parseLong(data);
                            numberList.add(foundCell);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Data nelze nacist: " + e.getMessage());
        }
    }

    public boolean isRowEmpty(Row row) {
        for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
            Cell cell = row.getCell(cellNum);
            if (cell != null) {
                return false;
            }
        }
        return true;
    }
}

