package duke.task;

import duke.exception.DukeException;
import duke.util.Parser;

import java.util.Date;

/**
 * A task which contains information about a Todo a date range.
 */
public class TodoWithDateRangeTask extends TodoTask {
    protected Date startDateTime;
    protected Date endDateTime;

    /**
     * Constructor for the TodoWithDateRangeTask class.
     *
     * @param description The description of the task.
     * @param startDateTime The start date & time of the task.
     * @param endDatetime The end date & time of the task.
     * @throws DukeException If the start date & time is after the end date & time.
     */
    public TodoWithDateRangeTask(String description, String startDateTime, String endDatetime) throws DukeException {
        super(description);
        this.startDateTime = Parser.parseDateTime(startDateTime);
        this.endDateTime = Parser.parseDateTime(endDatetime);
        if (startDateTime.compareTo(endDatetime) > 0) {
            throw new DukeException("Start date & time cannot be after end date & time!");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toStorageFormat() {
        return String.join(
            " | ",
            new String[] {
                "T",
                getIsDone() ? "1" : "0",
                super.description,
                Parser.convertDateTimeToString(startDateTime),
                Parser.convertDateTimeToString(endDateTime)
            }
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + getDetails() + " (between: "
            + Parser.convertDateTimeToString(startDateTime) + " and "
            + Parser.convertDateTimeToString(endDateTime) + ")";
    }
}
