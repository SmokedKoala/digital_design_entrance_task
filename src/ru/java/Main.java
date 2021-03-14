package ru.java;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputStr;
        inputStr = scanner.nextLine();
        if (isValid(inputStr))
            System.out.println(strConvertion(inputStr.toCharArray()));
        else
            System.out.println("the string is not valid");
    }

    public static String strConvertion(char[] inputStr){

        String strToReturn = "";
//        счётчик поможет нам отличить скобки внутри скобок
        int numOfBrackets = 0;
//        позиция открывающей скобки
        int indexOfFirstBracket = 0;
//        коэффициент повторений символов внутри скобок
        int ratio = 0;

        for (int i = 0; i < inputStr.length;i++){
//            если символ - буква вне скобок, добавить к возвращаемой строке
            if (Character.isLetter(inputStr[i]) && numOfBrackets == 0)
                strToReturn+=inputStr[i];

//            если символ - число вне скобок, присвоить значение переменной
            if (Character.isDigit(inputStr[i]) && numOfBrackets == 0)
                ratio = Integer.parseInt(String.valueOf(inputStr[i]));

//            если символ - открывающая скобка, то изменить счётчик,
//            и если внешняя - зафиксировать позицию
            if (inputStr[i] == '['){
                numOfBrackets++;
                if (numOfBrackets == 1)
                    indexOfFirstBracket = i;
            }

//           если символ - закрывающая скобка, то изменить счётчик,
//           и если внешняя - зафиксировать позицию
            if (inputStr[i] == ']'){
                numOfBrackets--;
                if (numOfBrackets == 0){
                    strToReturn += strConvertion(Arrays.copyOfRange(inputStr,indexOfFirstBracket+1,i)).repeat(ratio);
                    indexOfFirstBracket = 0;
                    ratio = 0;
                }
            }
        }
        return strToReturn;
    }

    public static boolean isValid(String inputStr){
        int numOfBrackets = 0;
        for (int i = 0; i<inputStr.length();i++){

//            является ли число только счётчиком
            if (Character.isDigit(inputStr.toCharArray()[i]))
                if (inputStr.toCharArray()[i+1]!='[') {
                    return false;
                }

            if (inputStr.toCharArray()[i] == '['){
                numOfBrackets++;
            }

//            есть ли закрывающая скобка перед открывающей
            if (inputStr.toCharArray()[i] == ']') {
                numOfBrackets--;
                if (numOfBrackets<0) {
                    return false;
                }
            }
        }
        //есть ли незакрытые скобки
        if (numOfBrackets!=0) {
            return false;
        }

        return (Pattern.matches("[a-zA-Z\\[\\]0-9]+",inputStr));
    }
}
