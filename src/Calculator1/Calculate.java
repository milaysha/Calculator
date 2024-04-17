package Calculator1;

import java.util.ArrayList;
import java.util.Scanner;

public class Calculate {
    public static void main(String[] args) throws Exception {
        Scanner con = new Scanner(System.in);

        System.out.println(calc(con.nextLine()));
    }


    public static String calc(String input) throws Exception {
        String[] value = input.split(" ");

        if (value.length > 3) {
            throw new Exception("Введите только два числа");
        }


        try {
            int result;
            int operand1 = romanToArabic(value[0]);
            int operand2 = romanToArabic(value[2]);
            if (operand1 < 0 || operand2 < 0) {
                throw new Exception("Введите положительное число");
            }
            if (value[1].equals("+")) {
                result = operand1 + operand2;
               return arabicToRoman(result);
            } else if (value[1].equals("-")) {
                result = operand1 - operand2;
                return arabicToRoman(result);
            } else if (value[1].equals("*")) {
                result = operand1 * operand2;
                return arabicToRoman(result);
            } else if (value[1].equals("/")) {
                result = operand1 / operand2;
                return arabicToRoman(result);
            } else {
                throw new Exception();
            }
        } catch (RuntimeException ex) {
            try {
                int operand1 = Integer.parseInt(value[0]);
                int operand2 = Integer.parseInt(value[2]);
                if (operand1 < 0 || operand2 < 0) {
                    throw new Exception("Введите положительное число");
                }
                int result2;
                if (value[1].equals("+")) {
                   return Integer.toString(operand1 + operand2);
                } else if (value[1].equals("-")) {
                    return Integer.toString(operand1 - operand2);
                } else if (value[1].equals("*")) {
                    return Integer.toString(operand1 * operand2);
                } else if (value[1].equals("/")) {
                    return Integer.toString(operand1 / operand2);
                } else {
                    throw new Exception();
                }

            } catch (NumberFormatException num) {
                throw new Exception();
            }
        }
    }

    public static int romanToArabic(String value) {
        if (value.equals("I")) {
            return 1;
        } else if (value.equals("II")) {
            return 2;
        } else if (value.equals("III")) {
            return 3;
        } else if (value.equals("IV")) {
            return 4;
        } else if (value.equals("V")) {
            return 5;
        } else if (value.equals("VI")) {
            return 6;
        } else if (value.equals("VII")) {
            return 7;
        } else if (value.equals("VIII")) {
            return 8;
        } else if (value.equals("IX")) {
            return 9;
        } else if (value.equals("X")) {
            return 10;
        }
        throw new RuntimeException(); //это непроверенное исключение в Java, которое возникает во время выполнения приложения.
    }

    public static String arabicToRoman(int value) {
        if (value == 0) {
            throw new RuntimeException();
        }
        String romRez = "";
        String[] roman = new String[]{"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C"};
        int[] arabic = new int[]{1, 4, 5, 9, 10, 40, 50, 90, 100};
        ArrayList<Integer> numbers = new ArrayList<>();
        while (value / 10 > 0) {
            numbers.add(value % 10);
            value = value / 10;
        }
        numbers.add(value);
        for (int i = numbers.size() - 1; i >= 0; i--) {
            int num = (int) (numbers.get(i) * Math.pow(10, i));
            if (num == 0) {
                continue;
            }
            for (int j = 0; j < arabic.length; j++) {
                if (num == arabic[j]) {
                    romRez += roman[j];
                    break;
                } else if (num < arabic[j]) {
                    romRez += roman[j - 1];
                    num = num - arabic[j - 1];
                    j = -1;
                }
            }

        }
        return romRez;
    }
}