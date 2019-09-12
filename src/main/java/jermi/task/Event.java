package jermi.task;

import static jermi.misc.Constant.EVENT_STRING_REPRESENTATION;
import static jermi.misc.Constant.EVENT_TYPE_CODE;
import static jermi.misc.Constant.TASK_UNDONE_STRING_REPRESENTATION;

import jermi.misc.TaskType;

/**
 * A representation of the event task.
 */
public class Event extends TaskWithDateTime {
    /**
     * Public constructor for class.
     * Creates an uncompleted event task.
     *
     * @param description Description of event.
     * @param dateTime Date and time of event.
     */
    public Event(String description, String dateTime) {
        this(description, dateTime, TASK_UNDONE_STRING_REPRESENTATION);
    }

    /**
     * Public constructor for class.
     * Creates a completed/uncompleted event task.
     *
     * @param description Description of event.
     * @param dateTime Date and time of event.
     * @param isDone "1" for completed event, else "0".
     */
    public Event(String description, String dateTime, String isDone) {
        super(description, dateTime, isDone);
    }

    /**
     * Returns task type code, "E".
     *
     * @return "E".
     */
    @Override
    String getTypeCode() {
        return EVENT_TYPE_CODE;
    }

    /**
     * Returns a string representation of task.
     *
     * @return A string representation of task.
     */
    @Override
    public String toString() {
        return String.format(EVENT_STRING_REPRESENTATION, super.toString(), this.getDateTime());
    }
}
