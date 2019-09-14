import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the deadline task given by the user.
 */
class Deadline extends TimedTask {

    Deadline(String taskDescription, String timestamp) throws DukeException {
        super(taskDescription, timestamp);
    }

    /**
     * Formats the task such that it can be outputted in a readable form for the user.
     *
     * @return The description of the task.
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", (isDone ? "\u2713" : "\u2717"),
                super.taskDescription, super.timestamp.format(super.dateTimeFormatter));
    }

}
