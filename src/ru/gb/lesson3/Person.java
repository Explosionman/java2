package ru.gb.lesson3;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private List<String> phones = new ArrayList<>();
    private List<String> emails = new ArrayList<>();
    private String secondName;

    public Person(String phone, String secondName, String email) {
        this.secondName = secondName;
        phones.add(phone);
        emails.add(email);
    }

    public void addPhoneNumber(String number) {
        phones.add(number);
    }

    public void addEmail(String number) {
        emails.add(number);
    }

    public List<String> getPhones() {
        return phones;
    }

    public String getSecondName() {
        return secondName;
    }

    public List<String> getEmails() {
        return emails;
    }
}
