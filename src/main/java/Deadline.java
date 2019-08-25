public class Deadline extends Task {
    private String date;

    public Deadline(String type, String name, String date) {
        super(type, name);
        this.date = date;
    }

    public Deadline(String type, String done, String name, String date) {
        super(type, done, name);
        this.date = date;
    }

    @Override
    public String fileFormat() {
        return String.format("%s | %s | %s | %s\n", type, done ? "1" : "0", name, date);
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)", type, done ? "v" : "x", name, date);
    }
}
