package ru.gb.lesson3;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class Main {
    private static LinkedHashSet<String> uniqueWords;
    private static LinkedHashMap<String, Integer> uniqueWordsCount;

    public static void main(String[] args) {
        //Задание №1
        //Зачем воровать стих, если можно написать самому =))
        String text = "Этот текст - для первого задания, " +
                "этот текст - для сплита понимания, " +
                "этот текст - будет нещадно разнесен в массив, " +
                "все символы будут рыдать на взрыв.";
        String[] textArray = text.split("[,.\\-\\s]+");

        System.out.println("Задание №1. Список неповторяющихся слов: " + findUniqueWords(textArray));
        System.out.println("Задание №1. Количество неповторяющихся слов: " + countChars(textArray));

        //Второе задание
        PhoneBook book = new PhoneBook();

        Person p1 = new Person("+12356583522", "Рыбинсков", "fisher@mail.ru");
        Person p6 = new Person("+12356583511", "Рыбинсков", "ffff@mail.ru");
        Person p5 = new Person("+12356583533", "Рыбинсков", "fish@mail.ru");
        p1.addPhoneNumber("+62583621144");
        Person p2 = new Person("+99996663355", "Иванов", "ivn@mail.ru");
        p2.addEmail("iv@mail.ru");
        Person p3 = new Person("+32165554488", "Петров", "ptr@mail.ru");
        Person p4 = new Person("+85553336598", "Иванов", "ivanov@mail.ru");
        p4.addPhoneNumber("+59688958789");

        book.addContact(p1);
        book.addContact(p2);
        book.addContact(p3);
        book.addContact(p4);
        book.addContact(p5);
        book.addContact(p6);

        String[] secondNamesToFind = {"Рыбинсков", "Иванов", "Петров"};
        for (int i = 0; i < secondNamesToFind.length; i++) {
            try {
                System.out.println("По фамилии " + secondNamesToFind[i] + " найдены телефоны: " + book.findPhoneNumbers(secondNamesToFind[i]));
                System.out.println("По фамилии " + secondNamesToFind[i] + " найдены e-mail: " + book.findEmail(secondNamesToFind[i]));
            } catch (Exception e) {
                e.getMessage();
                e.printStackTrace();
            }
        }
    }

    private static LinkedHashSet<String> findUniqueWords(String[] wordsArray) {
        uniqueWords = new LinkedHashSet<>();
        for (int i = 0; i < wordsArray.length; i++) {
            uniqueWords.add(wordsArray[i]);
        }
        return uniqueWords;
    }

    private static LinkedHashMap<String, Integer> countChars(String[] wordsArray) {
        uniqueWordsCount = new LinkedHashMap<>();
        for (int i = 0; i < wordsArray.length; i++) {
            if (uniqueWordsCount.containsKey(wordsArray[i])) {
                uniqueWordsCount.put(wordsArray[i], uniqueWordsCount.get(wordsArray[i]) + 1);
            } else {
                uniqueWordsCount.put(wordsArray[i], 1);
            }
        }
        return uniqueWordsCount;
    }
}
