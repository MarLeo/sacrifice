package model;

import lombok.Data;

@Data
public class Partner extends Person {

    public Partner(String name, int age) {
        super(name, age);
    }
}
