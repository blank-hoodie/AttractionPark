package Human.Height;

public class Height {
    private double value; // в сантиметрах

    public Height(double value) {
        if (value <= 0) throw new IllegalArgumentException("Рост должен быть положительным");
        this.value = value;
    }

    public double getValue() { return value; }

    public void setValue(double value) {
        if (value <= 0) throw new IllegalArgumentException("Рост должен быть положительным");
        this.value = value;
    }

    @Override
    public String toString() { return (int) value + " см"; }
}