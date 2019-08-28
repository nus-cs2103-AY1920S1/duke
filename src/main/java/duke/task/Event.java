package duke.task;

import duke.time.DateTime;

public class Event extends Task {
    private String dateTime;
    // Constructor
    public Event(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    // Getters & Setters
    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + DateTime.parseDateTime(dateTime) + ")";
    }

    @Override
    public String toStorage() {
        int isDoneInt = isDone ? 1 : 0;
        return String.format("%d | E | %s | %s", isDoneInt, description, dateTime);
    }
}
