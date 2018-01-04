package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Student {

    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String about;
}