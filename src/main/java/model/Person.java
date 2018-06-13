package model;

import java.util.List;

public interface Person {
    public void setFirstName(String name);
    public void setLastName(String name);
    public String getFirstName();
    public String getLastName();
    Country getCountry();
    int getAge();
    float getHeight();
    boolean isProgrammer();
    List<Contact> getContacts();
    boolean isBroke();
    Person withBroke(boolean broke);
}