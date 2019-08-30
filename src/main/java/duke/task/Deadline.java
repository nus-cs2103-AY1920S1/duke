package duke.task;

import java.util.Date;

public class Deadline extends Task {
    Date deadline;

    public Deadline(String content, Date deadline) {
        super(content);
        this.deadline = deadline;
    }

    @Override
    public String getTime() {
        return inputFormatter.format(deadline);
    }

    @Override
    public String toString() {
        return done ? String.format("[D][%c] %s (by: %s)", tick, content, outputFormatter.format(deadline))
                : String.format("[D][%c] %s (by: %s)", cross, content, outputFormatter.format(deadline));
    }
}