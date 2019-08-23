package main.task;

public class Deadline extends Task {
    String date;

    public Deadline(String description, String date) {
        super(description.trim());
        this.date = date;
    }

    @Override
    public String toString() {
        String displayDate = "by: " + this.date.substring(3);
        return "[D][" + super.getStatusIcon() + "] "
                + super.description + " (" + displayDate + ")";
    }

    public String toDataFormat() {
        return "D | " + super.getStatusIcon() + " | " + super.description + " | " + date;
    }
}
