package duke.task;

import java.util.Date;

public class Event extends Task {
    Date time;

    public Event(String content, Date time) {
        super(content);
        this.time = time;
    }

    @Override
    public String getTime() {
        return inputFormatter.format(time);
    }

    @Override
    public String toString() {
        return done ? String.format("[E][%c] %s (at: %s)", tick, content, outputFormatter.format(time))
                : String.format("[E][%c] %s (at: %s)", cross, content, outputFormatter.format(time));
    }
}
