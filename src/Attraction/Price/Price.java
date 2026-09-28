package Attraction.Price;

public class Price {
    private double value;

    public Price(double value) {
        if (value < 0) throw new IllegalArgumentException("Цена не может быть отрицательной");
        this.value = value;
    }

    public double getValue() { return value; }

    public void setValue(double value) {
        if (value < 0) throw new IllegalArgumentException("Цена не может быть отрицательной");
        this.value = value;
    }

    @Override
    public String toString() { return String.format("%.2f", value); }
}