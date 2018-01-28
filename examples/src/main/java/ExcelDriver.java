import generics.CircularBuffer;
import model.Person;
import org.apache.log4j.Logger;

import java.util.Arrays;

public class ExcelDriver {

    private static final Logger logger = Logger.getLogger(ExcelDriver.class);

    private static final String path = "catalogTest.xlsx";

    public static void main(String[] args) {

        //File file = ExcelUtilities.readResource(path);

        // logger.info(String.format("File Name: %s", file.getName()));

        /* ChienDao dao =  new PoiChienDao(file.getAbsolutePath());

        logger.info( String.format("Liste d'animaux: %s",  dao.findAllChiens()));
        */

        /*Components components = new Components.Builder()
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
        */
        //logger.info( String.format("Liste de composants: %s",  dao.findAllComponents()));

        /*CircularBuffer<String> buffer = new CircularBuffer<>(10);

        buffer.offer("a");
        buffer.offer("bc");
        buffer.offer("d");

        System.out.println("result = " + concatenate(buffer));*/


        Person p1 = new Person("Don Draper", 89);
        Person p2 = new Person("Peggy Olson", 65);

        Person[] madMen = {p1, p2};

        Person p3 = new Person("Bert Cooper", 100);

        madMen = add(p3, madMen);


        System.out.println(Arrays.toString(madMen));


    }

    private static Person[] add(final Person p3, Person[] array) {

        final int length = array.length;
        array = Arrays.copyOf(array, length + 1);
        array[length] = p3;
        return array;
    }

    private static String concatenate(CircularBuffer<String> buffer) {
        StringBuilder result = new StringBuilder();

        String value;
        while ((value = buffer.poll()) != null) {
            result.append(value);
        }

        return result.toString();
    }

}
