package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A Task with specific start and end dates/times.
 */
public class Event extends Task {
    private LocalDateTime time;

    /**
     * Instantiates a new Event Task with a given description and time.
     * @param description The task's description as a String.
     * @param time The task's time as a LocalDateTime object.
     */
    public Event(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }

    /**
     * Returns the task's type, done status, description and time as a String.
     * @return A String in the format: [E][+] task_description (at: dd/MM/yyyy, hh:mmPM)
     */
    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy, h:mma");
        return String.format(
                "[E][%s] %s (at: %s)",
                isDone ? "+" : " ",
                description,
                time.format(dateTimeFormatter)
        );
    }
}
