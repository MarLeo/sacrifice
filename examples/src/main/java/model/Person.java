package model;

import lombok.Data;

import java.util.Objects;

@Data
public final class Person {


    private final String name;
    private final int age;


    public Person(String name, int age) {

        Objects.requireNonNull(name);
        this.name = name;
        this.age = age;
    }


    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + age;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return age == person.getAge() && name.equals(person.getName());
    }


}
