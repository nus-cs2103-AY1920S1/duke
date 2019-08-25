import java.util.Date;

class Event extends Task {
    Date date;

    Event(String task, Date date) {
        super(task);
        this.date = date;
    }

    @Override
    public String toString() {
        String iconForDone = done ? "v" : "x";
        return String.format("[E][%s] %s (at: %s)", iconForDone, this.task, this.date.toString());
    }
}