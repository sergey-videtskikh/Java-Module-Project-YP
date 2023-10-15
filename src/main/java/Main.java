import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int numberOfPerson = getNumberOfPersonFromConsole(sc);

        BarCalculator calculator = new BarCalculator(numberOfPerson);

        getItemsFromConsole(sc, calculator);

        printItems(calculator);

        printMoneyForOnePerson(calculator);
    }

    private static int getNumberOfPersonFromConsole(Scanner sc) {
        int numberOfPerson = 0;
        while (numberOfPerson <= 1) {
            System.out.println("На скольких человек необходимо разделить счёт?");
            try {
                String next = sc.next();
                numberOfPerson = Integer.parseInt(next);
                if (numberOfPerson <= 1) {
                    System.out.println("Введёное число меньше либо равно 1. " +
                            "Необходимо ввести число больше 1. Повторите ввод.");
                }
            } catch (RuntimeException exception) {
                System.out.println("Вы ввели не число. Повтворите ввод.");

            }
        }

        return numberOfPerson;
    }

    private static void getItemsFromConsole(Scanner sc, BarCalculator calculator) {
        boolean continuing = true;
        while (continuing) {
            Item item = getItemFromConsole(sc);
            calculator.addItem(item);
            System.out.println("Вы успешно добавили товар: " + item);
            continuing = askIfContinue(sc);
        }
    }

    private static Item getItemFromConsole(Scanner sc) {
        System.out.println("Введите название товара:");
        String name = sc.next();
        double cost = 0;
        while (cost <= 0) {
            System.out.println("Введите стоимость товара в формате ХХ.ХХ :");
            try {
                String next = sc.next();
                cost = Double.parseDouble(next);
            } catch (RuntimeException exception) {
                System.out.println("Вы ввели некорректное значение стоимости товара. Повторите ввод.");
            }
            if (cost <= 0) {
                System.out.println("Стоимость товара должна быть больше 0.00. Повторите ввод.");
            }
        }

        return new Item(name, cost);
    }

    private static boolean askIfContinue(Scanner sc) {
        System.out.println("Если хотите закончить добавление товара введите 'Завершить' иначе введите любой символ:");
        String s = sc.next();

        return !s.equalsIgnoreCase("завершить");
    }

    private static void printItems(BarCalculator calculator) {
        System.out.println("Добавленные товары:");
        List<Item> items = calculator.getItems();
        items.forEach(System.out::println);
    }

    private static void printMoneyForOnePerson(BarCalculator calculator) {
        System.out.println("Необходимо заплатить каждому:");
        System.out.println(getStringMoneyForOnePerson(calculator));
    }

    private static String getStringMoneyForOnePerson(BarCalculator calculator) {
        double moneyForOnePerson = calculator.getMoneyForOnePerson();
        double remainderOfFloor = Math.floor(moneyForOnePerson) % 10;
        if (remainderOfFloor == 1) {
            return String.format("%.2f рубль", moneyForOnePerson);
        } else if (remainderOfFloor >= 2 && remainderOfFloor <= 4) {
            return String.format("%.2f рубля", moneyForOnePerson);
        } else {
            return String.format("%.2f рублей", moneyForOnePerson);
        }
    }
}