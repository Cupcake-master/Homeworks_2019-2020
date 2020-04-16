package ru.bulat;

public class Line {
    private int ID;
    private String firstName;
    private String country;

    public Line(int ID, String firstName, String country) {
        this.ID = ID;
        this.firstName = firstName;
        this.country = country;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
