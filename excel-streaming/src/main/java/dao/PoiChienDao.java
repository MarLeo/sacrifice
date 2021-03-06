package dao;

import api.Chien;
import api.ChienDao;
import enums.RaceDeChien;
import enums.Sexe;
import model.SimpleChien;
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

public class PoiChienDao implements ChienDao {

    private static final Logger logger = Logger.getLogger(PoiChienDao.class);

    private String fileName;

    // TODO : creer des objets génériques, utiliser un design pattern pour l' instantiation: builder et factory

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

    @Override
    public List<Chien> findAllChiens() throws IOException {

        final File file = new File(fileName);

        final List<Chien> chiens = new ArrayList<>();

        try (final Workbook workbook = getWorkBook(file)) {
            int sheets = workbook.getNumberOfSheets();
            for (int i = 0; i < sheets; i++) {
                final Sheet sheet = workbook.getSheetAt(i);
                logger.info("Sheet Name: " + sheet.getSheetName());
                for (Row row : sheet) {
                    if (row.getRowNum() == 0) {
                        logger.info("HEADERS: " + headers(sheet.getRow(0)).values());
                    } else {
                        final Chien chien = rowToChien(row);
                        chiens.add(chien);
                    }
                }
            }
        }

        return chiens;
    }




}
