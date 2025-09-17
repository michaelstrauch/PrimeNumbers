
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
                        if (firstRow != row) {
                            switch (cell.getCellType()) {
                                case STRING, BLANK -> {
                                    if (cell.getStringCellValue().matches("(\\s*[0-9]+)\\s*")) {
                                        data = formatter.formatCellValue(cell).trim();
                                        numberList.add(Long.parseLong(data));
                                    } else {
                                        continue;
                                    }
                                }
                                case NUMERIC -> numberList.add((long) cell.getNumericCellValue());
                            }
                            foundCell = Long.parseLong(data);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Data nelze nacist: " + e.getMessage());
        }
    }
}

