package model;

import lombok.*;
import lombok.experimental.Wither;
import org.springframework.stereotype.Component;

import java.util.List;

@Setter
@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@Component("person")
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

    @Wither
    boolean broke;

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