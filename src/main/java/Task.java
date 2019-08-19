public class Task {
    private static int total = 1;
    private int id;
    private String description;

    Task(String description) {
        this.id = total++;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return id + ". " + description;
    }
}
