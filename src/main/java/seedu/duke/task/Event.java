package seedu.duke.task;

import seedu.duke.core.DukeException;
import seedu.duke.statistic.Statistic;
import java.time.LocalDateTime;

/**
 * Event class is a subclass of Task class.
 * Additional attribute is the String location, which holds the venue of the Event task.
 */
public class Event extends Task {

    private String location;

    /**
     * Returns an Event object after initializing with 2 Strings; description and location.
     *
     * @param description Description String of the task.
     * @param location Location String of the task.
     */
    public Event(String description, String location) throws DukeException {
        super(description);
        this.location = location;
        taskType = PossibleTaskTypes.EVENT;
    }

    /**
     * Returns an Event object after initializing with 2 Strings; description and location;
     * and Boolean status of the task.
     *
     * @param description Description String of the task.
     * @param location Location String of the task.
     * @param isDone isDone Boolean status of the task.
     * @param createDateTime LocalDateTime object.
     * @param lastModifiedDateTime LocalDateTime object.
     */
    public Event(String description, String location, Boolean isDone, LocalDateTime createDateTime,
                 LocalDateTime lastModifiedDateTime) {
        super(description, isDone, createDateTime, lastModifiedDateTime);
        this.location = location;
        taskType = PossibleTaskTypes.EVENT;
    }

    /**
     *  Returns a parsed String of the Event object.
     *  Eg. description = "meeting", location = "LT19", isDone = false.
     *  Parsed string = "[E][✘] meeting  (at: LT19)".
     *
     * @return Parsed String of the Event object.
     */
    @Override
    public String toString() {
        assert !this.location.isEmpty() : "Empty location should be handled by Duke Exception during input";
        return "[E]" + super.toString() + " (at: " + location + ")";
    }

    /**
     * Returns a parsed String, meant for saving, of the Event object.
     * Eg. description = "meeting", location = "LT19", isDone = false.
     * Parsed saved string = "E | 0 | meeting  | LT19".
     *
     * @return Parsed saved String of the Event object.
     */
    @Override
    public String toSaveString() {
        assert !this.location.isEmpty() : "Empty location should be handled by Duke Exception during input";
        return ("E" + super.toSaveString() + " | " + this.getLocation() + " | "
                + this.getCreateDateTime().toString() + " | " + this.getLastModifiedDateTime().toString());
    }

    /**
     * Getter function for the string Location.
     *
     * @return String location.
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * Setter function for Boolean isDone.
     *
     * @param stats Statistic object.
     */
    @Override
    public void setDone(Statistic stats) {
        super.setDone(stats);
        stats.incrementTotalEventsCompleted();
    }

    /**
     * Getter function for taskType.
     *
     * @return Character taskType.
     */
    public char getTaskType() {
        return 'E';
    }

}
