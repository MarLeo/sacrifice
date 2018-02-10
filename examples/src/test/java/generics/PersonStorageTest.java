package generics;

import model.*;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PersonStorageTest {


    Partner donDraper = new Partner("Don Draper", 89);
    Partner peggyOlson = new Partner("Peggy Olson", 65);
    Employee bertCooper = new Employee("Bert Cooper", 100);


    private File file;
    private PersonSaver saver;
    private PersonLoader loader;


    @Test
    public void saveAndLoadsPerson() throws Exception {
        Person person = new Person("Bob", 20);
        saver.save(person);
        //saver.save(donDraper);

        assertEquals(person, loader.load());
        //assertEquals(donDraper, loader.load());
    }

    @Test
    public void savesAndLoadsArraysOfPeople() throws Exception {
        List<Partner> persons = new ArrayList<>();
        persons.add(donDraper);
        //persons.add(bertCooper);

        saver.saveAll(persons);

        assertEquals(donDraper, loader.load());
        assertEquals(bertCooper, loader.load());
    }


    @Test
    public void loadListsOfPeople() throws Exception {

        saver.save(donDraper);
        saver.save(peggyOlson);

        List<Person> people = new ArrayList<>();
        loader.loadAll(people);

        assertEquals(donDraper, people.get(0));
        assertEquals(peggyOlson, people.get(1));


    }


}
