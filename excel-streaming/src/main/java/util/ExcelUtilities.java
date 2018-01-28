package util;

import com.google.common.base.Splitter;
import model.Enclosures;
import model.Modules;
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


    //<editor-fold desc="Convert String to List">
    public static List<String> stringToList(final String s) {
        return Splitter.on(",")
                .trimResults()
                .splitToList(s);
    }
    //</editor-fold>

    //<editor-fold desc="Read Cell Type">
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
    //</editor-fold>

    //<editor-fold desc="Read Headers">
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
    //</editor-fold>

    //<editor-fold desc="Read Excel file">
    public static File readResource(final String path) {
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        return new File(Objects.requireNonNull(loader.getResource(path)).getFile());
    }
    //</editor-fold>

    //<editor-fold desc="Read All Sheets">
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
    //</editor-fold>

    //<editor-fold desc="Read Components Sheet">
    public static model.Components rowToComponents(final Row row) {
        model.Components.Builder components = new model.Components.Builder();

        Iterator<Cell> cell = row.cellIterator();

        while (cell.hasNext()) {
            Cell nextCell = cell.next();
            int columnIndex = nextCell.getColumnIndex();

            switch (columnIndex) {
                case 0:
                    final String componentType = (String) getCellValue(nextCell);
                    components.componentType(componentType);
                    break;
                case 1:
                    final double reference = (double) getCellValue(nextCell);
                    components.reference(Double.toString(reference));
                    break;
                case 2:
                    final String description = (String) getCellValue(nextCell);
                    components.description(description);
                    break;
                case 3:
                    final String manufacturer = (String) getCellValue(nextCell);
                    components.manufacturer(manufacturer);
                    break;
                case 4:
                    final String productRange = (String) getCellValue(nextCell);
                    components.productRange(productRange);
                    break;
            }
        }
        return components.build();
    }
    //</editor-fold>

    //<editor-fold desc="Read Enclosures Sheet">
    public static Enclosures rowToEnclosures(final Row row) {
        Enclosures.Builder enclosures = new Enclosures.Builder();

        Iterator<Cell> cell = row.cellIterator();

        while (cell.hasNext()) {
            Cell nextCell = cell.next();
            int columnIndex = nextCell.getColumnIndex();

            switch (columnIndex) {
                case 0:
                    final String componentType = (String) getCellValue(nextCell);
                    enclosures.componentType(componentType);
                    break;
                case 1:
                    final String reference = (String) getCellValue(nextCell);
                    enclosures.reference(reference);
                    break;
                case 2:
                    final String description = (String) getCellValue(nextCell);
                    enclosures.description(description);
                    break;
                case 3:
                    final String manufacturer = (String) getCellValue(nextCell);
                    enclosures.manufacturer(manufacturer);
                    break;
                case 4:
                    final String productRange = (String) getCellValue(nextCell);
                    enclosures.productRange(productRange);
                    break;
                case 5:
                    final double width = (double) getCellValue(nextCell);
                    enclosures.width((int) width);
                    break;
                case 6:
                    final double height = (double) getCellValue(nextCell);
                    enclosures.width((int) height);
                    break;
                case 7:
                    final double depth = (double) getCellValue(nextCell);
                    enclosures.depth((int) depth);
                    break;
                case 8:
                    final double verticalOffset = (double) getCellValue(nextCell);
                    enclosures.verticalOffset((int) verticalOffset);
                    break;
                case 9:
                    final double horizontalOffset = (double) getCellValue(nextCell);
                    enclosures.horizontalOffset((int) horizontalOffset);
                    break;
                case 10:
                    final double numberOfDoors = (double) getCellValue(nextCell);
                    enclosures.numberOfDoors((int) numberOfDoors);
                    break;
                case 11:
                    final String wmOrFs = (String) getCellValue(nextCell);
                    enclosures.wmOrFs(wmOrFs);
                    break;
            }
        }
        return enclosures.build();
    }
    //</editor-fold>

    //<editor-fold desc="Read Module Sheet">
    public static Modules rowToModules(Row row) {

        Modules.Builder modules = new Modules.Builder();

        Iterator<Cell> cell = row.cellIterator();

        while (cell.hasNext()) {
            Cell nextCell = cell.next();
            int columnIndex = nextCell.getColumnIndex();

            switch (columnIndex) {
                case 0:
                    final String componentType = (String) getCellValue(nextCell);
                    modules.componentType(componentType);
                    break;
                case 1:
                    final String tag = (String) getCellValue(nextCell);
                    modules.tag(tag);
                    break;
                case 2:
                    final String reference = (String) getCellValue(nextCell);
                    modules.reference(reference);
                    break;
                case 3:
                    final String description = (String) getCellValue(nextCell);
                    modules.description(description);
                    break;
                case 4:
                    final String manufacturer = (String) getCellValue(nextCell);
                    modules.manufacturer(manufacturer);
                    break;
                case 5:
                    final String productRange = (String) getCellValue(nextCell);
                    modules.productRange(productRange);
                    break;
                case 6:
                    final String hierarchyGroup = (String) getCellValue(nextCell);
                    modules.hierarchyGroup(hierarchyGroup);
                    break;
                case 7:
                    final String referenceModule = (String) getCellValue(nextCell);
                    modules.referenceModule(referenceModule);
                    break;
                case 8:
                    final String referenceComment = (String) getCellValue(nextCell);
                    modules.referenceComment(referenceComment);
                    break;
                case 9:
                    final double qty = (double) getCellValue(nextCell);
                    modules.qty((int) qty);
                    break;
                case 10:
                    final String positionConnector = (String) getCellValue(nextCell);
                    modules.positionConnector(positionConnector);
                    break;
                case 11:
                    final double frontDepth = (double) getCellValue(nextCell);
                    modules.frontDepth((int) frontDepth);
                    break;
                case 12:
                    final double rearDepth = (double) getCellValue(nextCell);
                    modules.rearDepth((int) rearDepth);
                    break;
                case 13:
                    final double frontDissipation = (double) getCellValue(nextCell);
                    modules.frontDissipation((int) frontDissipation);
                    break;
                case 14:
                    final double rearDissipation = (double) getCellValue(nextCell);
                    modules.rearDissipation((int) rearDissipation);
                    break;
                default:
                    break;
            }
        }
        return modules.build();
    }
    //</editor-fold>



}
