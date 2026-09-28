package Human;

import Human.Age.Age;
import Human.Height.Height;
import Human.Weight.Weight;

import java.util.ArrayList;
import java.util.List;

public abstract class Human {

    protected String name;
    protected double money;
    protected List<String> visitedAttractions;
    protected List<String> eatenDishes;

    public Human(String name, double money) {
        this.name = name;
        this.money = money;
        this.visitedAttractions = new ArrayList<>();
        this.eatenDishes = new ArrayList<>();
    }

    public String getName() { return name; }
    public double getMoney() { return money; }
    public List<String> getVisitedAttractions() { return visitedAttractions; }
    public List<String> getEatenDishes() { return eatenDishes; }

    public void setMoney(double money) {
        if (money < 0) throw new IllegalArgumentException("Деньги не могут быть отрицательными");
        this.money = money;
    }

    public boolean pay(double amount) {
        if (amount > money) {
            System.out.println(name + " не хватает денег!");
            return false;
        }
        money -= amount;
        return true;
    }

    public void addVisited(String attractionName) {
        visitedAttractions.add(attractionName);
    }

    public void addEaten(String dish) {
        eatenDishes.add(dish);
    }

    public abstract String getRole();

    @Override
    public String toString() {
        return String.format("%s{role='%s', name='%s', money=%.2f}",
                getClass().getSimpleName(), getRole(), name, money);
    }

    // ==================== СОТРУДНИК ====================
    public static class Employee extends Human {
        private String attractionName;

        public Employee(String name, double money, String attractionName) {
            super(name, money);
            this.attractionName = attractionName;
        }

        public String getAttractionName() { return attractionName; }

        public void setAttractionName(String attractionName) {
            this.attractionName = attractionName;
        }

        @Override
        public String getRole() { return "Сотрудник"; }

        public void serve(Visitor visitor) {
            System.out.printf("%s (сотрудник '%s') обслуживает %s%n",
                    name, attractionName, visitor.getName());
        }

        @Override
        public String toString() {
            return String.format("Employee{name='%s', money=%.2f, attraction='%s'}",
                    name, money, attractionName);
        }
    }

    // ==================== ПОСЕТИТЕЛЬ ====================
    public static class Visitor extends Human {
        private Age age;
        private Weight weight;
        private Height height;

        public Visitor(String name, double money, Age age, Weight weight, Height height) {
            super(name, money);
            this.age = age;
            this.weight = weight;
            this.height = height;
        }

        public Age getAge() { return age; }
        public Weight getWeight() { return weight; }
        public Height getHeight() { return height; }

        public void setAge(Age age) { this.age = age; }
        public void setWeight(Weight weight) { this.weight = weight; }
        public void setHeight(Height height) { this.height = height; }

        @Override
        public String getRole() { return "Посетитель"; }

        @Override
        public String toString() {
            return String.format("Visitor{name='%s', money=%.2f, age=%s, weight=%s, height=%s}",
                    name, money, age, weight, height);
        }
    }
}