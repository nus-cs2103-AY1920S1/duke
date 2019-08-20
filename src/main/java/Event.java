public class Event extends Task {
    private String date;

    public Event(String name, String date) {
        super(name);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s(at: %s)", done ? "v" : "x", name, date);
    }
}
