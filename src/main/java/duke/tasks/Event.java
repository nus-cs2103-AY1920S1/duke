package duke.tasks;

import duke.Task;

public class Event extends Task {
    protected String date;
    public Event(String desc, String date) {
        super(desc);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("[E][%c] %s (at: %s)", getStatusChar(), description, date);
    }
}
