package model;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@Component("person")
@EqualsAndHashCode
public class UsualPerson implements Person {
    private long id;
    private String firstName;
    private String lastName;
    private Country country;
    private int age;
    private float height;
    private boolean isProgrammer;
    @Singular
    private List<Contact> contacts;

   public String toString() {
        String s = "Name: " + firstName + " " + lastName + "\n"
                + "Age: " + age + "\n"
                + "Height: " + height + "\n"
                + "Country: " + country + "\n"
                + "Is Programmer?: " + isProgrammer + "\n";
        if ((contacts != null) && (!contacts.isEmpty())) {
            s += "Contacts: ";
            for (Contact contact : contacts) {
                s += contact + ", ";
            }
            s += "\n";
        }
        return s;
    }
}