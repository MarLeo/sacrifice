import generics.CircularBuffer;
import model.Person;
import org.apache.log4j.Logger;
import util.Util;

import java.util.*;
import java.util.function.Predicate;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.partitioningBy;

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


        Person donDraper = new Person("Don Draper", 89);
        Person peggyOlson = new Person("Peggy Olson", 65);
        Person bertCooper = new Person("Bert Cooper", 100);


        List<Person> madMen = new ArrayList<>();
        madMen.add(donDraper);
        madMen.add(peggyOlson);
        madMen.add(bertCooper);

        //Collections.sort(madMen, new Util.AgeComparator());
        Collections.sort(madMen, new Util.ReverseComparator<>(new Util.AgeComparator()));

        // System.out.println(madMen);

        final Person youngestCastMember = min(madMen, new Util.AgeComparator());

        System.out.println("Youngest Man: " + youngestCastMember);

        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);

        System.out.println("Min: " + min(numbers, Integer::compare));

        /**
         * Wildcards
         * Upper Bounded       |  Lower Bounded       |  Unbounded
         * List<? extends Cls> |  List<? super Cls>  |   List<?>
         */

        Predicate<Person> isOld = person -> person.getAge() > 80;
        System.out.println(isOld.test(donDraper));
        System.out.println(isOld.test(bertCooper));

        Map<Boolean, Long> oldAndYoungPeople = madMen.stream()
                .collect(partitioningBy(person -> person.getAge() > 80, counting()));


        System.out.println(oldAndYoungPeople);


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

    private static <T> T min(List<T> values, Comparator<T> comparator) {

        if (values.isEmpty()) throw new IllegalArgumentException("List is empty, cannot find minimum");
        T lowestElement = values.get(0);
        for (int i = 1; i < values.size(); i++) {
            final T element = values.get(i);
            if (comparator.compare(element, lowestElement) < 0) {
                lowestElement = element;
            }
        }

        return lowestElement;
    }


}
