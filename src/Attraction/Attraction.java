package Attraction;

import Attraction.Name.Name;
import Attraction.Price.Price;
import Attraction.Restriction.Restriction;
import Human.Human;

public abstract class Attraction {

    // Общее ограничение для всех аттракционов парка
    private static final Restriction RESTRICTION = new Restriction();

    protected Name name;
    protected Price price;

    public Attraction(Name name, Price price) {
        this.name = name;
        this.price = price;
    }

    public Name getName() { return name; }
    public Price getPrice() { return price; }
    public Restriction getRestriction() { return RESTRICTION; }

    // Проверка: пропускает ли аттракцион посетителя
    public boolean canRide(Human human) {
        if (!RESTRICTION.check(human)) {
            return false;
        }
        if (human.getMoney() < price.getValue()) {
            System.out.printf("%s не хватает денег (цена %.2f)%n",
                    human.getName(), price.getValue());
            return false;
        }
        return true;
    }

    public abstract void ride(Human human);

    @Override
    public String toString() {
        return String.format("%s{название='%s', цена=%.2f, ограничение=[%s]}",
                getClass().getSimpleName(), name, price.getValue(), RESTRICTION);
    }

    // ==================== КОНКРЕТНЫЕ АТТРАКЦИОНЫ ====================

    public static class RollerCoasterAttraction extends Attraction {
        public RollerCoasterAttraction(String title, Price price) {
            super(new Name.RollerCoaster(title), price);
        }

        @Override
        public void ride(Human human) {
            if (!canRide(human)) return;
            human.pay(price.getValue());
            human.addVisited(name.getTitle());
            System.out.printf("🎢 %s мчится на горках '%s'!%n",
                    human.getName(), name.getTitle());
        }
    }

    public static class HorrorAttraction extends Attraction {
        public HorrorAttraction(String title, Price price) {
            super(new Name.Horror(title), price);
        }

        @Override
        public void ride(Human human) {
            if (!canRide(human)) return;
            human.pay(price.getValue());
            human.addVisited(name.getTitle());
            System.out.printf("👻 %s дрожит от страха в '%s'!%n",
                    human.getName(), name.getTitle());
        }
    }

    public static class CarsAttraction extends Attraction {
        public CarsAttraction(String title, Price price) {
            super(new Name.Cars(title), price);
        }

        @Override
        public void ride(Human human) {
            if (!canRide(human)) return;
            human.pay(price.getValue());
            human.addVisited(name.getTitle());
            System.out.printf("🏎️ %s гоняет на машинках '%s'!%n",
                    human.getName(), name.getTitle());
        }
    }
}