package utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class excelUtility {

    private Workbook workbook;

    public excelUtility(String excelFilePath) throws IOException {
        FileInputStream fis = new FileInputStream(excelFilePath);
        workbook = new XSSFWorkbook(fis);
    }

    public int getRowCount(String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            throw new IllegalArgumentException("Sheet " + sheetName + " does not exist");
        }
        return sheet.getPhysicalNumberOfRows();
    }

    public int getCellCount(String sheetName, int rowIndex) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            throw new IllegalArgumentException("Sheet " + sheetName + " does not exist");
        }
        Row row = sheet.getRow(rowIndex);
        if (row == null) {
            throw new IllegalArgumentException("Row " + rowIndex + " does not exist in sheet " + sheetName);
        }
        return row.getPhysicalNumberOfCells();
    }

    public String getCellData(String sheetName, int rowIndex, int colIndex) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            throw new IllegalArgumentException("Sheet " + sheetName + " does not exist");
        }
        Row row = sheet.getRow(rowIndex);
        if (row == null) {
            throw new IllegalArgumentException("Row " + rowIndex + " does not exist in sheet " + sheetName);
        }
        Cell cell = row.getCell(colIndex);
        if (cell == null) {
            throw new IllegalArgumentException("Cell at row " + rowIndex + " and column " + colIndex + " does not exist in sheet " + sheetName);
        }
        return cell.toString();
    }
}