package ru.gb.lesson2;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String numbers = "10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0";
        //Ниже два объекта для проверки работы исключений при преобразовании теста в двумерный массив:
        // String badNumbers1 = "10 3 1 2\n2 3 2 2\n5 6 7 1";
        //String badNumbers2 = "3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0";

        //Пример для проверки исключения по преобразованию String в int:
//        String badNumbers3 = "3 1 2 4\n2 3 2 2\n5 6 7 1\n300s 3 1 0";
        try {
            System.out.println("Двумерный массив по заданию №1: " + Arrays.deepToString(parseToArray(numbers)));
            //Для проверки работы исключения:
//            System.out.println(Arrays.deepToString(parseToArray(badNumbers1)));
            //Для проверки работы исключения:
//            System.out.println(Arrays.deepToString(parseToArray(badNumbers2)));
        } catch (Array4x4Exception ex) {
            System.out.print(ex.getMessage());
            System.out.println(ex.getArrayLength());
            ex.printStackTrace();
        }

        try {
            System.out.println("Ответ по заданию №2: " + makeCalculations(parseToArray(numbers)));
            //Для проверки работы исключения:
//            System.out.println("Ответ по заданию №2: " + makeCalculations(parseToArray(badNumbers3)));
        } catch (ParseToIntException ex) {
            System.out.print(ex.getMessage());
            System.out.println(ex.getErrorOffSet());
            ex.printStackTrace();
        } catch (Array4x4Exception ex) {
            System.out.print(ex.getMessage());
            System.out.println(ex.getArrayLength());
            ex.printStackTrace();
        }
    }

    //Проверяем слово на принадлежность к числу (Логика: Если все символы по отдельности = числу, значит слово = числу)
    private static boolean isOnlyDigits(String s) throws ParseToIntException {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (!Character.isDigit(chars[i])) {
                throw new ParseToIntException("Неудачная попытка преобразовать в число: ", s);
            }
        }
        return true;
    }

    public static String[][] parseToArray(String text) throws Array4x4Exception {
        String[] firstArray = text.split("\n");
        if (firstArray.length != 4) {
            throw new Array4x4Exception("Длина массива после разбивки по \"\\n\" не равна 4, текущая длина: ", firstArray);
        }

        String[][] secondArray = new String[firstArray.length][firstArray.length];
        for (int i = 0; i < firstArray.length; i++) {
            secondArray[i] = firstArray[i].split(" ");
            if (secondArray[i].length != 4) {
                throw new Array4x4Exception("Длина массива после разбивки по \" \"  не равна 4, текущая длина:  ", secondArray[i]);
            }
        }
        return secondArray;
    }

    public static int makeCalculations(String[][] arr) throws ParseToIntException {
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                //Насколько понял на практике, здесь бросать новое исключение не нужно, т.к. оно бросится из метода проверки
//                if (isOnlyDigits(arr[i][j]) == false) {
//                    throw new ParseToIntException("Неудачная попытка преобразовать в число: ", arr[i][j]);
//                } else {
//                    result += Integer.parseInt(arr[i][j]);
//                }
                if (isOnlyDigits(arr[i][j])) {
                    result += Integer.parseInt(arr[i][j]);
                }
            }
        }
        return result / 2;
    }
}
