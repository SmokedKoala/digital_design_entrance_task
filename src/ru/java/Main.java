package ru.java;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputStr;
        inputStr = scanner.nextLine();
        System.out.println(strConvertion(inputStr.toCharArray()));
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
}
