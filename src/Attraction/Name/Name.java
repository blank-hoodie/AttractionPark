package Attraction.Name;

public abstract class Name {
    protected String title;

    public Name(String title) { this.title = title; }
    public String getTitle() { return title; }

    @Override
    public String toString() { return title; }

    // ==================== ВЛОЖЕННЫЕ НАЗВАНИЯ ====================
    public static class RollerCoaster extends Name {
        public RollerCoaster(String title) { super(title); }
    }

    public static class Horror extends Name {
        public Horror(String title) { super(title); }
    }

    public static class Cars extends Name {
        public Cars(String title) { super(title); }
    }
}