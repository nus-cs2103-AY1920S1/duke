package duke.task;

import java.time.LocalDateTime;

/**
 * This is one kind of <code>Task</code> that specifies the description and due date for the item in the task list.
 */
public class Deadline extends Task {

    private LocalDateTime dueDateTime;

    /**
     * This is a class constructor specifying the description and the due dateTime. The
     * <code>isDone</code> status is set to be <code>false</code> by default.
     *
     * @param dueDateTime a <code>LocalDateTime</code> object to specify the due date and time
     */
    public Deadline(String description, LocalDateTime dueDateTime) {
        super(description);
        this.dueDateTime = dueDateTime;
    }

    /**
     * {@inheritDoc} This is a class constructor specifying the description, the due dateTime ane the
     * <code>isDone</code> status.
     *
     * @param dueDateTime a <code>LocalDateTime</code> object to specify the due date and time
     */
    public Deadline(String description, LocalDateTime dueDateTime, boolean isDone) {
        super(description, isDone);
        this.dueDateTime = dueDateTime;
    }

    public LocalDateTime getDueDateTime() {
        return dueDateTime;
    }

    /**
     * {@inheritDoc}
     *
     * @return a string representing this todo in the format of a tag "[D]" at the start, followed by the description
     *         and the due date time
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DATE_TIME_FORMATTER.format(dueDateTime) + ")";
    }

    /**
     * Compares two <code>Task</code> objects by their descriptions and <code>isDone</code> status. The comparison is
     * mainly used for JUnit tests.
     *
     * @param obj the object to be compared
     * @return <code>true</code> if the specifications for two tasks are all the same;
     *         <code>false</code> otherwise.
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
        Deadline another = (Deadline) obj;
        boolean isSameDescription = this.description.equals(another.description);
        boolean isSameStatus = this.isDone == another.isDone;
        boolean isSameDueDateTime = this.dueDateTime.equals(another.dueDateTime);
        return isSameDescription && isSameStatus && isSameDueDateTime;
    }
}
