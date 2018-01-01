package util;

import com.google.common.base.Splitter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExcelUtilities {


    public static List<String> stringToList(final String s) {
        return Splitter.on(",")
                .trimResults()
                .splitToList(s);
    }

    public static Object getCellValue(Cell cell) {
        switch (cell.getCellTypeEnum()) {
            case ERROR:
                return cell.getErrorCellValue();
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return cell.getNumericCellValue();
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case FORMULA:
                return cell.getCellFormula();
        }
        return null;
    }

    public static Map<Integer, String> headers(final Row row) {

        Map<Integer, String> headers = new TreeMap<>();
        Iterator<Cell> cell = row.cellIterator();

        while (cell.hasNext()) {
            Cell nextCell = cell.next();
            int columnIndex = nextCell.getColumnIndex();
            headers.put(columnIndex, (String) getCellValue(nextCell));
        }

        return headers;
    }

    public static File readResource(final String path) {
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        return new File(Objects.requireNonNull(loader.getResource(path)).getFile());
    }

    public static Workbook getWorkBook(final File file) throws IOException {
        Workbook workbook;

        FileInputStream stream = new FileInputStream(file);
        final String fileName = file.getName();

        if (fileName.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(stream); // for excel from 2007
        } else if (fileName.endsWith("xls")) {
            workbook = new HSSFWorkbook(stream); // for excel from 2003
        } else {
            throw new IllegalArgumentException(fileName + " is not an excel file, the extension should be xlsx or xls");
        }

        return workbook;
    }

}
