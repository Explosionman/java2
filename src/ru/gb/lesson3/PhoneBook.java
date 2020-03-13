package ru.gb.lesson3;

import java.util.HashMap;
import java.util.List;

public class PhoneBook {
    private HashMap<String, Person> book = new HashMap<>();
    private List<String> phones;
    private List<String> emails;

    public void addContact(Person person) {
        if (book.containsKey(person.getSecondName())) {
            String secondName = person.getSecondName();
            phones = person.getPhones();
            emails = person.getEmails();
            book.get(secondName).getPhones().addAll(person.getPhones());
            book.get(secondName).getEmails().addAll(person.getEmails());
            return;
        }
        book.put(person.getSecondName(), person);
    }

    public List<String> findPhoneNumbers(String secondName) throws IllegalArgumentException {
        if (book.get(secondName) == null) {
            throw new IllegalArgumentException("Введенная фамилия: \"" + secondName + "\" отсутствует в PhoneBook");
        }
        return book.get(secondName).getPhones();
    }

    public List<String> findEmail(String secondName) {
        return book.get(secondName).getEmails();
    }
}
