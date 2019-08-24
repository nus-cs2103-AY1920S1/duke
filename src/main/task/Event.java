package main.task;

public class Event extends Task {
    Date date;
    Time time;

    public Event(String description, String date, String time) {
        super(description.trim());
        this.date = new Date(date);
        this.time = new Time(time);
    }

    @Override
    public String toString() {
        String displayDate = "at: " + this.date;
        return "[E][" + super.getStatusIcon() + "] "
                + super.description + " (" + displayDate + ")";
    }
}
