package Food;

import Food.DishName.DishName;
import Food.PlaceName.PlaceName;
import Human.Human;

public abstract class Food {

    protected PlaceName placeName;
    protected DishName dishName;
    protected double price;
    protected int calories;
    protected int stock;

    public Food(PlaceName placeName, DishName dishName, double price, int calories, int stock) {
        this.placeName = placeName;
        this.dishName = dishName;
        this.price = price;
        this.calories = calories;
        this.stock = stock;
    }

    public PlaceName getPlaceName() { return placeName; }
    public DishName getDishName() { return dishName; }
    public double getPrice() { return price; }
    public int getCalories() { return calories; }
    public int getStock() { return stock; }

    public void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("Цена не может быть отрицательной");
        this.price = price;
    }

    public void sell(Human human) {
        if (stock <= 0) {
            System.out.printf("В '%s' закончилось: %s%n", placeName, dishName);
            return;
        }
        if (human.getMoney() < price) {
            System.out.printf("%s не хватает денег на '%s' в '%s'%n",
                    human.getName(), dishName, placeName);
            return;
        }
        human.pay(price);
        human.addEaten(placeName + " — " + dishName);
        stock--;
        System.out.printf("%s купил '%s' в '%s' за %.2f (%d ккал). Осталось денег: %.2f%n",
                human.getName(), dishName, placeName, price, calories, human.getMoney());
    }

    public abstract void cook();

    @Override
    public String toString() {
        return String.format("%s{заведение='%s', блюдо='%s', цена=%.2f, ккал=%d, порций=%d}",
                getClass().getSimpleName(), placeName, dishName, price, calories, stock);
    }

    // ==================== КОНКРЕТНЫЕ ЗАВЕДЕНИЯ ====================

    public static class Cafe extends Food {
        public Cafe(String place, String dish, double price, int calories, int stock) {
            super(new PlaceName(place), new DishName(dish), price, calories, stock);
        }

        @Override
        public void cook() {
            System.out.printf("☕ В '%s' варят '%s'%n", placeName, dishName);
        }
    }

    public static class FastFood extends Food {
        public FastFood(String place, String dish, double price, int calories, int stock) {
            super(new PlaceName(place), new DishName(dish), price, calories, stock);
        }

        @Override
        public void cook() {
            System.out.printf("🍔 В '%s' жарят '%s'%n", placeName, dishName);
        }
    }

    public static class IceCreamStand extends Food {
        public IceCreamStand(String place, String dish, double price, int calories, int stock) {
            super(new PlaceName(place), new DishName(dish), price, calories, stock);
        }

        @Override
        public void cook() {
            System.out.printf("🍦 В '%s' готовят '%s'%n", placeName, dishName);
        }
    }
}