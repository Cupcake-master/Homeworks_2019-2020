package sample;

import java.util.Objects;

public class Person {
    private String email;
    private String password;
    private String nickname;
    private String country;

    public Person(String email, String password, String nickname, String country) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Person{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return email.equals(person.email) &&
                password.equals(person.password) &&
                nickname.equals(person.nickname) &&
                country.equals(person.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, nickname, country);
    }
}
