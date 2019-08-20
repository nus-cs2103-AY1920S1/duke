public class Deadline extends Task {
    private String date;

    public Deadline(String name, String date) {
        super(name);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s(by: %s)", done ? "v" : "x", name, date);
    }
}
