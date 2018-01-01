package dao;

import api.Components;
import model.Enclosures;
import model.Modules;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static util.ExcelUtilities.*;

public class ComponentsDao implements Components<model.Components> {

    private static final Logger logger = Logger.getLogger(ComponentsDao.class);

    private String fileName;

    public ComponentsDao(String fileName) {
        this.fileName = fileName;
    }



    @Override
    public List<model.Components> findAllComponents() throws IOException {

        final File file = new File(fileName);

        List<model.Components> components = null;

        try (final Workbook workbook = getWorkBook(file)) {
            int sheets = workbook.getNumberOfSheets();
            logger.info(String.format("Number of Sheets: %d", sheets));

            for (int i = 0; i < sheets; i++) {

                components = new ArrayList<>();
                final Sheet sheet = workbook.getSheetAt(i);
                final String sheetName = sheet.getSheetName();

                logger.info("Sheet Name: " + sheetName);

                for (Row row : sheet) {

                    if (row.getRowNum() == 0) {
                        logger.info("HEADERS: " + headers(sheet.getRow(0)).values());
                    } else if (sheetName.equals("Electrical Components")) {
                        final model.Components component = rowToComponents(row);
                        components.add(component);
                    } else if (sheetName.equals("Enclosures")) {
                        final Enclosures enclosures = rowToEnclosures(row);
                        components.add(enclosures);
                    } else if (sheetName.equals("Modules")) {
                        final Modules modules = rowToModules(row);
                        components.add(modules);
                    }
                }
                logger.info(String.format("Liste de composants: %s", components));
            }
        }
        return components;
    }

}
