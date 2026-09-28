package Food.DishName;

public class DishName {
    private String title;

    public DishName(String title) { this.title = title; }
    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    @Override
    public String toString() { return title; }
}