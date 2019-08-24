package main.task;

public class Deadline extends Task {
    Date date;

    public Deadline(String description, String date) {
        super(description.trim());
        this.date = new Date(date);
    }

    @Override
    public String toString() {
        String displayDate = "by: " + this.date.toString();
        return "[D][" + super.getStatusIcon() + "] "
                + super.description + " (" + displayDate + ")";
    }
}
