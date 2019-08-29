package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A Task with a specific end date/time.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Instantiates a new Deadline Task with a given description and deadline.
     * @param description The task's description as a String.
     * @param deadline The task's deadline as a LocalDateTime object.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Returns the task's type, done status, description and deadline as a String.
     * @return A String in the format: [D][+] task_description (by: dd/MM/yyyy, hh:mmPM)
     */
    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy, h:mma");
        return String.format(
                "[D][%s] %s (by: %s)",
                isDone ? "+" : " ",
                description,
                deadline.format(dateTimeFormatter)
        );
    }
}
