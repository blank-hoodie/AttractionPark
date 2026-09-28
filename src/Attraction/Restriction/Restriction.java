package Attraction.Restriction;

import Human.Human;

public class Restriction {

    public static final double MIN_HEIGHT = 140; // см
    public static final double MAX_WEIGHT = 120; // кг
    public static final int    MIN_AGE    = 12;  // лет

    // Проверка: проходит ли человек по общим ограничениям парка
    public boolean check(Human human) {
        if (!(human instanceof Human.Visitor)) {
            return true; // сотрудников не проверяем
        }
        Human.Visitor v = (Human.Visitor) human;

        if (v.getHeight().getValue() < MIN_HEIGHT) {
            System.out.printf("%s не проходит: рост %d см (нужно не менее %d см)%n",
                    v.getName(), (int) v.getHeight().getValue(), (int) MIN_HEIGHT);
            return false;
        }
        if (v.getWeight().getValue() > MAX_WEIGHT) {
            System.out.printf("%s не проходит: вес %d кг (нужно не более %d кг)%n",
                    v.getName(), (int) v.getWeight().getValue(), (int) MAX_WEIGHT);
            return false;
        }
        if (v.getAge().getValue() < MIN_AGE) {
            System.out.printf("%s не проходит: возраст %d лет (нужно не менее %d лет)%n",
                    v.getName(), v.getAge().getValue(), MIN_AGE);
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("рост ≥ %d см, вес ≤ %d кг, возраст ≥ %d лет",
                (int) MIN_HEIGHT, (int) MAX_WEIGHT, MIN_AGE);
    }
}