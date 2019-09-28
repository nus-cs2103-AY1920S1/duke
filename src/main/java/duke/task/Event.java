package duke.task;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * This is one kind of <code>Task</code> that specifies the description and the duration for the item in the task list.
 */
public class Event extends Task {

    private LocalDateTime startDateTime;
    private LocalTime endTime;

    /**
     * {@inheritDoc} This is a class constructor specifying the description, start time and end time for the task. The
     * <code>isDone</code> status is set to be <code>false</code> by default.
     * F
     *
     * @param startDateTime a <code>LocalDateTime</code> object to specify the start date and time of this event
     * @param endTime       a <code>LocalTime</code> object to specify the end time of this event
     */
    public Event(String description, LocalDateTime startDateTime, LocalTime endTime) {
        super(description);
        this.startDateTime = startDateTime;
        this.endTime = endTime;
    }

    /**
     * {@inheritDoc} This is a class constructor specifying the description, start time, end time, and
     * <code>isDone</code> status of the task.
     *
     * @param startDateTime a <code>LocalDateTime</code> object to specify the start date and time of this event
     * @param endTime       a <code>LocalTime</code> object to specify the end time of this event
     */
    public Event(String description, LocalDateTime startDateTime, LocalTime endTime, boolean isDone) {
        super(description, isDone);
        this.startDateTime = startDateTime;
        this.endTime = endTime;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * {@inheritDoc}
     *
     * @return a string representing this todo in the format of a tag "[E]" at the start, followed by the description,
     *         the start date time, and the end time.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + DATE_TIME_FORMATTER.format(startDateTime) + " - " + endTime + ")";
    }

    /**
     * Compares two <code>Task</code> objects by their descriptions and <code>isDone</code> status. The comparison is
     * mainly used for JUnit tests.
     *
     * @param obj the object to be compared
     * @return <code>true</code> if the specifications for two tasks are all the same;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        //@@author ZhangHuafan-reused
        //Reused from https://www.javaworld.com/article/3305792/comparing-java-objects-with-equals-and-hashcode.html
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        //@@author
        Event another = (Event) obj;
        boolean isSameDescription = this.description.equals(another.description);
        boolean isSameStatus = this.isDone == another.isDone;
        boolean isSameStartDateTime = this.startDateTime.equals(another.startDateTime);
        boolean isSameEndTime = this.endTime.equals(another.endTime);
        return isSameDescription && isSameStatus && isSameStartDateTime && isSameEndTime;
    }
}
