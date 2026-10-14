package utils;

import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class ExcleReader {

    private ExcleReader(){

    }

    public static List<Map<String, String>> read(String file, String sheetName){
        Path path = Path.of(file);
        if(!Files.exists(path)){
            throw new IllegalStateException("Excel file not found: " + path.toAbsolutePath());
        }

        try (Workbook workbook = WorkbookFactory.create(path.toFile())) {
            Sheet sheet = workbook.getSheet(sheetName);
            if(sheet == null){
                throw new IllegalStateException("Sheet '" + sheetName + "' not found in " + file);
            }

            DataFormatter formatter = new DataFormatter();

            int firstRowNum = sheet.getFirstRowNum();
            Row header = sheet.getRow(firstRowNum);
            if (header == null) {
                return new ArrayList<>();
            }

            List<String> columns = new ArrayList<>();
            for(Cell cell : header){
                columns.add(formatter.formatCellValue(cell).trim());
            }

            List<Map<String, String>> rows = new ArrayList<>();
            for (int i = firstRowNum + 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Map<String, String> rowMap = new LinkedHashMap<>();
                for (int j = 0; j < columns.size(); j++) {
                    Cell cell = row.getCell(j, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    String value = cell != null ? formatter.formatCellValue(cell).trim() : "";
                    rowMap.put(columns.get(j), value);
                }
                rows.add(rowMap);
            }
            return rows;

        } catch (IOException e) {
            throw new IllegalStateException("Could not read Excel file " + file, e);
        }
    }
}