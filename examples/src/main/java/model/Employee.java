package model;

import lombok.Data;

@Data
public class Employee extends Person {

    public Employee(String name, int age) {
        super(name, age);
    }

}
