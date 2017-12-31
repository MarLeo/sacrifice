package dao;

import api.Chien;
import api.ChienDao;
import com.google.common.base.Splitter;
import enums.RaceDeChien;
import enums.Sexe;
import model.SimpleChien;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class PoiChienDao implements ChienDao {

    private static final Logger logger = Logger.getLogger(PoiChienDao.class);

    private String fileName;

    public PoiChienDao(String fileName) {
        super();
        this.fileName = fileName;
    }

    private static Chien rowToChien(final Row row) {

        SimpleChien chien = new SimpleChien();

        Iterator<Cell> cell = row.cellIterator();

        while (cell.hasNext()) {
            Cell nextCell = cell.next();
            int columnIndex = nextCell.getColumnIndex();

            switch (columnIndex) {
                case 0:
                    final String nom = (String) getCellValue(nextCell);
                    chien.setNom(nom);
                    break;
                case 1:
                    final String nomComplet = (String) getCellValue(nextCell);
                    chien.setNomComplet(nomComplet);
                    break;
                case 2:
                    final double codeSexe = (double) getCellValue(nextCell);
                    final Sexe sexe = Sexe.valueOfByCode((int) codeSexe);
                    chien.setSexe(sexe);
                    break;
                case 3:
                    final String codeRace = (String) getCellValue(nextCell);
                    final RaceDeChien race = RaceDeChien.valueOfByCode(codeRace);
                    chien.setRace(race);
                    break;
                case 4:
                    final String couleurs = (String) getCellValue(nextCell);
                    chien.setCouleurs(stringToList(couleurs));
                    break;
                case 5:
                    final double poids = (double) getCellValue(nextCell);
                    chien.setPoids(poids);
                    break;
            }
        }
        return chien;
    }

    private static List<String> stringToList(final String s) {
        return Splitter.on(",")
                .trimResults()
                .splitToList(s);
    }

    private static Object getCellValue(Cell cell) {
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

    @Override
    public List<Chien> findAllChiens() {

        final File file = new File(fileName);

        final List<Chien> chiens = new ArrayList<>();

        try {

            final Workbook workbook = WorkbookFactory.create(file);
            //final Sheet sheet = workbook.getSheet("Feuil1");
            int sheets = workbook.getNumberOfSheets();

            for (int i = 0; i < sheets; i++) {
                final Sheet sheet = workbook.getSheetAt(i);
                for (Row row : sheet) {
                    if (row.getRowNum() == 0) {
                        System.out.println("HEADERS: " + headers(sheet.getRow(0)).values());
                    } else {
                        final Chien chien = rowToChien(row);
                        chiens.add(chien);
                    }
                }

            }
        } catch (InvalidFormatException | IOException e) {
            logger.error(e.getMessage(), e);
        }
        return chiens;
    }

    public Map<Integer, String> headers(final Row row) {

        Map<Integer, String> headers = new TreeMap<>();
        Iterator<Cell> cell = row.cellIterator();

        while (cell.hasNext()) {
            Cell nextCell = cell.next();
            int columnIndex = nextCell.getColumnIndex();
            headers.put(columnIndex, (String) getCellValue(nextCell));
        }

        return headers;
    }


}
