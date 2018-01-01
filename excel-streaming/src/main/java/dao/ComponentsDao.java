package dao;

import api.Components;
import model.Enclosures;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static util.ExcelUtilities.*;

public class ComponentsDao implements Components<model.Components> {

    private static final Logger logger = Logger.getLogger(ComponentsDao.class);

    private String fileName;

    public ComponentsDao(String fileName) {
        this.fileName = fileName;
    }

    private static model.Components rowToComponents(final Row row) {
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

    private static Enclosures rowToEnclosures(final Row row) {
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

    @Override
    public List<model.Components> findAllComponents() throws IOException {

        final File file = new File(fileName);

        List<model.Components> components = null;

        try (final Workbook workbook = getWorkBook(file)) {
            int sheets = workbook.getNumberOfSheets();
            for (int i = 0; i < sheets; i++) {
                components = new ArrayList<>();
                final Sheet sheet = workbook.getSheetAt(i);
                final String sheetName = sheet.getSheetName();
                logger.info("Sheet Name: " + sheetName);
                for (Row row : sheet) {
                    if (row.getRowNum() == 0) {
                        logger.info("HEADERS: " + headers(sheet.getRow(0)).values());
                    } else if (sheetName.equals("Enclosures")) {
                        final Enclosures enclosures = rowToEnclosures(row);
                        components.add(enclosures);
                    } else if (sheetName.equals("Electrical Components")) {
                        final model.Components component = rowToComponents(row);
                        components.add(component);
                    }
                }
                logger.info(String.format("Liste de composants: %s", components));
                return components;
            }
        }
        return null;
    }


}
