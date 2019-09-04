package duke.task;

import duke.time.DateTime;

public class Event extends Task {
    private String dateTime;

    // Constructor
    public Event(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    // Trying out using varargs.
    public Event(String ... strings) {
        super(strings[0]);
        this.dateTime = strings[1];
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
