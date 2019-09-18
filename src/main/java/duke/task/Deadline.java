package duke.task;

import duke.Parser;
import duke.exception.DukeUnknownInputException;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static duke.Parser.DEADLINE_FORMAT_PATTERN;
import static duke.Parser.LOCALE;
import static duke.task.TaskType.DEADLINE;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private String deadline;

    /**
     * Creates a new Deadline task.
     *
     * @param description the task description.
     * @param deadline the deadline to complete the task by, of format "dd/MM/yy HHmm".
     * @throws DukeUnknownInputException if arguments are in incorrect format.
     */
    Deadline(String description, String deadline) throws DukeUnknownInputException {
        this(description, deadline, false);
    }

    /**
     * Creates a new Deadline task.
     *
     * @param description the deadline task description.
     * @param deadline the deadline to complete the task by, of format "dd/MM/yy HHmm".
     * @param isDone whether is task is marked done or not.
     * @throws DukeUnknownInputException if arguments are in incorrect format.
     */
    public Deadline(String description, String deadline, boolean isDone) throws DukeUnknownInputException {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * Transforms the instance's deadline string into a ZonedDateTime object.
     *
     * @return the ZonedDateTime object.
     */
    public ZonedDateTime getZonedDateTime() {
        return Parser.parseDateTime(deadline, DEADLINE);
    }

    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        ZonedDateTime dateTime = getZonedDateTime();
        DateTimeFormatter deadlineFormatter = DateTimeFormatter.ofPattern(DEADLINE_FORMAT_PATTERN, LOCALE);
        return String.format("[D]%s (by: %s)", super.toString(), dateTime.format(deadlineFormatter));
    }
}
