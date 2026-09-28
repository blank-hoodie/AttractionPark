package Human.Weight;

public class Weight {
    private double value; // в кг

    public Weight(double value) {
        if (value <= 0) throw new IllegalArgumentException("Вес должен быть положительным");
        this.value = value;
    }

    public double getValue() { return value; }

    public void setValue(double value) {
        if (value <= 0) throw new IllegalArgumentException("Вес должен быть положительным");
        this.value = value;
    }

    @Override
    public String toString() { return value + " кг"; }
}