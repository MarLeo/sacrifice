import dao.ComponentsDao;
import model.Components;
import model.Enclosures;
import org.apache.log4j.Logger;
import util.ExcelUtilities;

import java.io.File;
import java.io.IOException;

public class ExcelDriver {

    private static final Logger logger = Logger.getLogger(ExcelDriver.class);

    private static final String path = "catalogTest.xlsx";

    public static void main(String[] args) throws IOException {

        File file = ExcelUtilities.readResource(path);

        logger.info(String.format("File Name: %s", file.getName()));

        /* ChienDao dao =  new PoiChienDao(file.getAbsolutePath());

        logger.info( String.format("Liste d'animaux: %s",  dao.findAllChiens()));
        */

        Components components = new Components.Builder()
                .componentType("Electrical Component")
                .reference("14811")
                .description("COMB BUSBAR 24 PITCHES- FOR C120 AND NG125- 63A UNI")
                .manufacturer("LECTRIC")
                .productRange("CELLEAN")
                .build();

        logger.info(String.format("Components data: %s", components));

        Enclosures enclosures = new Enclosures.Builder<>()
                .componentType("Electrical Component")
                .reference("14811")
                .description("COMB BUSBAR 24 PITCHES- FOR C120 AND NG125- 63A UNI")
                .manufacturer("LECTRIC")
                .productRange("CELLEAN")
                .width(400)
                .height(400)
                .depth(200)
                .verticalOffset(50)
                .horizontalOffset(50)
                .numberOfDoors(1)
                .wmOrFs("WM")
                .build();

        logger.info(String.format("Enclosures data: %s", enclosures));


        ComponentsDao dao = new ComponentsDao(file.getAbsolutePath());
        dao.findAllComponents();
        //logger.info( String.format("Liste de composants: %s",  dao.findAllComponents()));

    }

}
