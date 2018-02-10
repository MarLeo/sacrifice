package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.List;

public class PersonLoader {

    private final RandomAccessFile file;

    public PersonLoader(final File file) throws FileNotFoundException {
        this.file = new RandomAccessFile(file, "rw");
    }

    public Person load() throws ClassCastException {

        return null;
    }


    // a person or everything that's is a parent of person for ex: an interface we've implemented
    public void loadAll(final List<? super Person> persons) throws ClassCastException {

    }


}
