package AttractionParc;

import Attraction.Attraction;
import Attraction.Price.Price;
import Food.Food;
import Human.Age.Age;
import Human.Height.Height;
import Human.Human;
import Human.Weight.Weight;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<Attraction> attractions = new ArrayList<>();
    static List<Food> foodPlaces = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("   ДОБРО ПОЖАЛОВАТЬ В ПАРК АТТРАКЦИОНОВ!");
        System.out.println("========================================\n");

        // ---------- 1. СОЗДАЁМ ПОСЕТИТЕЛЯ ----------
        Human.Visitor visitor = createVisitor(scanner);
        System.out.printf("%nПривет, %s! У тебя %.2f руб.%n", visitor.getName(), visitor.getMoney());

        // ---------- 2. ЗАПОЛНЯЕМ ПАРК ----------
        fillPark();

        // ---------- 3. МЕНЮ ----------
        boolean running = true;
        while (running) {
            System.out.println("\n========= МЕНЮ =========");
            System.out.println("1. Показать аттракционы");
            System.out.println("2. Показать еду");
            System.out.println("3. Покататься на аттракционе");
            System.out.println("4. Поесть");
            System.out.println("5. Мой отчёт");
            System.out.println("0. Выйти");
            System.out.print("Выбор: ");

            int choice = readInt(scanner);

            switch (choice) {
                case 1 -> showAttractions();
                case 2 -> showFood();
                case 3 -> rideAttraction(scanner, visitor);
                case 4 -> eatFood(scanner, visitor);
                case 5 -> showReport(visitor);
                case 0 -> running = false;
                default -> System.out.println("Неверный выбор.");
            }
        }

        // ---------- 4. ФИНАЛЬНЫЙ ОТЧЁТ ----------
        System.out.println("\n========= ИТОГОВЫЙ ОТЧЁТ =========");
        showReport(visitor);
        System.out.println("\nСпасибо за посещение парка! 🎡");
        scanner.close();
    }

    // ==================== СОЗДАНИЕ ПОСЕТИТЕЛЯ ====================
    static Human.Visitor createVisitor(Scanner scanner) {
        System.out.println("--- Заполните данные посетителя ---");

        System.out.print("Имя: ");
        String name = scanner.nextLine();

        System.out.print("Деньги (руб): ");
        double money = readDouble(scanner);

        System.out.print("Возраст (лет): ");
        int age = readInt(scanner);

        System.out.print("Рост (см): ");
        double height = readDouble(scanner);

        System.out.print("Вес (кг): ");
        double weight = readDouble(scanner);

        return new Human.Visitor(
                name, money,
                new Age(age),
                new Weight(weight),
                new Height(height));
    }

    // ==================== НАПОЛНЕНИЕ ПАРКА ====================
    static void fillPark() {
        attractions.add(new Attraction.RollerCoasterAttraction(
                "Стальные гонки", new Price(300)));
        attractions.add(new Attraction.HorrorAttraction(
                "Дом ужасов", new Price(250)));
        attractions.add(new Attraction.CarsAttraction(
                "Бешеные машинки", new Price(200)));

        foodPlaces.add(new Food.Cafe("Уютное кафе", "Капучино", 120, 150, 10));
        foodPlaces.add(new Food.FastFood("Бургер Кинг", "Чизбургер", 200, 450, 5));
        foodPlaces.add(new Food.IceCreamStand("Холодок", "Пломбир", 80, 200, 3));
    }

    // ==================== ПОКАЗ СПИСКОВ ====================
    static void showAttractions() {
        System.out.println("\n--- АТТРАКЦИОНЫ ---");
        for (int i = 0; i < attractions.size(); i++) {
            Attraction a = attractions.get(i);
            System.out.printf("%d. %s — %.2f руб.%n",
                    i + 1, a.getName().getTitle(), a.getPrice().getValue());
        }
        System.out.println("Ограничение: " + attractions.get(0).getRestriction());
    }

    static void showFood() {
        System.out.println("\n--- ЕДА ---");
        for (int i = 0; i < foodPlaces.size(); i++) {
            Food f = foodPlaces.get(i);
            System.out.printf("%d. [%s] %s — %.2f руб. (%d ккал, осталось: %d)%n",
                    i + 1, f.getPlaceName(), f.getDishName(),
                    f.getPrice(), f.getCalories(), f.getStock());
        }
    }

    // ==================== ДЕЙСТВИЯ ====================
    static void rideAttraction(Scanner scanner, Human.Visitor visitor) {
        showAttractions();
        System.out.print("\nВыбери аттракцион (номер): ");
        int idx = readInt(scanner) - 1;

        if (idx < 0 || idx >= attractions.size()) {
            System.out.println("Неверный номер.");
            return;
        }

        Attraction a = attractions.get(idx);
        System.out.println();
        a.ride(visitor);
    }

    static void eatFood(Scanner scanner, Human.Visitor visitor) {
        showFood();
        System.out.print("\nВыбери, что поесть (номер): ");
        int idx = readInt(scanner) - 1;

        if (idx < 0 || idx >= foodPlaces.size()) {
            System.out.println("Неверный номер.");
            return;
        }

        Food f = foodPlaces.get(idx);
        System.out.println();
        f.cook();
        f.sell(visitor);
    }

    // ==================== ОТЧЁТ ====================
    static void showReport(Human.Visitor visitor) {
        System.out.println("\n--- Отчёт о посещении ---");
        System.out.printf("Имя: %s%n", visitor.getName());
        System.out.printf("Осталось денег: %.2f руб.%n", visitor.getMoney());
        System.out.printf("Возраст: %s, рост: %s, вес: %s%n",
                visitor.getAge(), visitor.getHeight(), visitor.getWeight());

        List<String> visited = visitor.getVisitedAttractions();
        if (visited.isEmpty()) {
            System.out.println("Аттракционы: (не посещал)");
        } else {
            System.out.println("Посещённые аттракционы:");
            for (String v : visited) System.out.println("  • " + v);
        }

        List<String> eaten = visitor.getEatenDishes();
        if (eaten.isEmpty()) {
            System.out.println("Еда: (ничего не ел)");
        } else {
            System.out.println("Что ел:");
            for (String e : eaten) System.out.println("  • " + e);
        }
    }

    // ==================== ВВОД ЧИСЕЛ ====================
    static int readInt(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Введи целое число: ");
            }
        }
    }

    static double readDouble(Scanner scanner) {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().trim().replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.print("Введи число: ");
            }
        }
    }
}