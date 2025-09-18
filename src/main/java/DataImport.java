
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.*;
import java.util.ArrayList;
import java.util.List;



public class DataImport {


    public List<Long> dataFromExcel(String filePath) {
        List<Long> numberList = new ArrayList<>();
        try (InputStream inputFile = getClass().getClassLoader().getResourceAsStream(filePath)) {
            if (inputFile != null) {
                XSSFWorkbook wb = new XSSFWorkbook(inputFile);
                XSSFSheet sheet = wb.getSheetAt(0);
                XSSFRow firstRow = sheet.getRow(0);
                DataFormatter formatter = new DataFormatter();
                String data = null;

                for (Row row : sheet) {
                    for (Cell cell : row) {
                        if (firstRow != row) {
                            switch (cell.getCellType()) {
                                case STRING, BLANK -> {
                                    if (cell.getStringCellValue().matches("(\\s*[0-9]+)\\s*")) {
                                        data = formatter.formatCellValue(cell).trim();
                                        numberList.add(Long.parseLong(data));
                                    }
                                }
                                case NUMERIC -> numberList.add((long) cell.getNumericCellValue());
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Data nelze nacist: " + e.getMessage());
        }
        return numberList;
    }
}

