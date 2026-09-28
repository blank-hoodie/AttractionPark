package Human.Age;

public class Age {
    private int value;

    public Age(int value) {
        if (value < 0) throw new IllegalArgumentException("Возраст не может быть отрицательным");
        this.value = value;
    }

    public int getValue() { return value; }

    public void setValue(int value) {
        if (value < 0) throw new IllegalArgumentException("Возраст не может быть отрицательным");
        this.value = value;
    }

    @Override
    public String toString() { return value + " лет"; }
}