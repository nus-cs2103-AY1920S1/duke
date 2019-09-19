package duke.task;

import duke.exception.InvalidDateTimeException;
import duke.parser.DateParser;
import java.util.Date;

/**
 * A deadline item is a task with description and the date the deadline task is due by. The deadline item can be
 * created, marked as done or deleted.
 */
public class Deadline extends Task {

    /**
     * Constructs a new deadline task with description and the date where the deadline task is due by that has not been
     * done.
     * @param description the description of the deadline task
     * @param by the date the task is due by
     * @throws InvalidDateTimeException if the date is invalid
     */
    public Deadline(String description, String by) throws InvalidDateTimeException {
        super(description, DateParser.parse(by));
    }

    /**
     * Constructs a new deadline task with description, the date where the deadline task is due by that has not been
     * done.
     * @param description the description of the deadline task
     * @param by the date the task is due by
     */
    public Deadline(String description, Date by) {
        super(description, by);
        assert(by != null);
    }

    /**
     * Encodes the deadline task to be stored in the storage.
     * @return a string representation of the encoded deadline task
     */
    public String encode() {
        return "deadline," + super.encode();
    }

    /**
     * Returns a string representation of the deadline task. The string
     * representation consist of ("[D]") with the string representation of the
     * task.
     * @return a string representation of the deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + super.taskDate.get() + ")";
    }

}
