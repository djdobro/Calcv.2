import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Calc {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение");
        String calc = scanner.nextLine();

        try {
            String result = calc(calc.trim());
            System.out.println(result);
        }

            catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
    }

    public static String calc(String input) {
        List<String> OP = new ArrayList<>(List.of("+", "-", "*", "/"));
        String op = null;
        input = input.replaceAll(" ", "");


        for (String operator : OP) {
            op = input.contains(operator) ? operator : null;
            if (op != null) break;
        }

        String[] oper = op != null ? input.split("\\" + op) : null;
            if (oper == null || oper.length != 2) {
                throw new RuntimeException("Превышено количество операндов");
            }

            if (isNumeric(oper[0]) && isNumeric(oper[1])) {
                int result = calcInArabic(Integer.parseInt(oper[0]), Integer.parseInt(oper[1]), op);
                return (String.valueOf(result));
            }

            char [] operand1 = oper[0].toCharArray();
            char [] operand2 = oper[1].toCharArray();
            char [] resultCharArray = new char[operand1.length + operand2.length];
            int index = 0;

            for (char item: operand1) {
                resultCharArray[index++] = item;
            }
            for (char item: operand2) {
                resultCharArray[index++] = item;
            }

            for (char i : resultCharArray) {
                String figure = Character.toString(i);
                if (!arabic.contains(figure)){
                    throw new RuntimeException();
                }
            }
            int a = romanToInt(operand1);
            int b = romanToInt(operand2);
            int resulintFromRoman = calcInArabic(a, b, op);
            if (resulintFromRoman < 0){
                throw new RuntimeException();
            }

            return (intToRoman(resulintFromRoman));
    }
     public static String intToRoman(int num){
        arabic[] values = arabic.values();
        StringBuilder roman = new StringBuilder();
        for (int i = values.length - 1; i >= 0; i--){
            while (num >= values[1].getValue()) {
                num = num - values[1].getValue();
                roman.append(values[i].name());
            }
        }
        return roman.toString();
     }
     public static int romanToInt(char [] operand){
        int result = 0;
        int prev = 0;
        for (int i = operand.length - 1; i >= 0; i--){
            int cur = arabic.getBySymbol(Character.toString(operand[i]));
            if (cur < prev) {
                result -= cur;
            }
            else {
                result += cur;
            }
        }
        return result;
     }
     public enum arabic {
        I(1), IV(4), V(5), IX(9), X(10), XL(40), L(50), XC(90), C(100);

        private final int value;
        arabic(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static String getByValue(int value) {
            for (arabic numeral : arabic.values()) {
                if (numeral.value == value) {
                    return numeral.name();
                }
            }
            throw new RuntimeException();
         }

        public static int getBySymbol(String symbol){
            for (arabic numeral : arabic.values()){
                if (numeral.name().equals(symbol)){
                    return numeral.value;
                }
            }
            throw new RuntimeException();
         }

        public static boolean contains(String test) {
            for (arabic c : arabic.values()){
                if (c.name().equals(test)) {
                    return true;
                }
            }
            return false;
         }
     }

    public static int calcInArabic(int a, int b, String op) {
        if (a < 1 || a > 10 || b < 1 || b > 10) {
            throw new RuntimeException("Введен неправильный диапазон");
        }

        switch (op) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b == 0) {
                    throw new RuntimeException("Деление на ноль");
                }
                return a / b;
            default:
                throw new RuntimeException("Неизвестный оператор: ");
        }
    }

    public static boolean isNumeric(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}