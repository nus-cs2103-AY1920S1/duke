import java.util.Date;

class Deadline extends Task {
    Date date;

    Deadline(String task, Date date) {
        super(task);
        this.date = date;
    }

    @Override
    public String toString() {
        String iconForDone = done ? "\u2713" : "\u2718";
        return String.format("[D][%s] %s (by: %s)", iconForDone, this.task, this.date.toString());
    }
}