package Food.PlaceName;

public class PlaceName {
    private String title;

    public PlaceName(String title) { this.title = title; }
    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    @Override
    public String toString() { return title; }
}