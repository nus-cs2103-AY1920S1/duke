package seedu.duke.task;

import java.time.LocalDateTime;

import seedu.duke.core.DukeException;
import seedu.duke.statistic.Statistic;

/**
 * Task class represents a Task created by the user.
 * Attribute description holds the Task description.
 * Attribute isDone holds the status of completion of a task.
 * Attribute possibleTaskTypes is an enumerated list of the possible task types ie DEFAULT, DEADLINE, EVENT, TODO.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected PossibleTaskTypes taskType;
    protected LocalDateTime createDateTime;
    protected LocalDateTime lastModifiedDateTime;


    /**
     * Returns a Task object after initializing with the String description.
     * By default, isDone is set to false.
     * Default constructor when creating from scratch.
     *
     * @param description Description String of the task.
     */
    public Task(String description) throws DukeException {

        if (description.trim().isBlank()) {
            throw new DukeException("Woah, the description should not be empty or blank."
                    + "\nOnly the Sith deals in absolutes");
        }

        this.description = description;
        this.isDone = false;
        this.taskType = PossibleTaskTypes.DEFAULT;
        this.createDateTime = LocalDateTime.now();
        this.lastModifiedDateTime = LocalDateTime.now();
    }

    /**
     * Returns a Task object from user input after initializing with the String description and isDone status.
     *
     * @param description Description String of the task.
     * @param isDone Boolean status of the task.
     * @param createDateTime LocalDateTime object.
     * @param lastModified LocalDateTime object.
     */
    public Task(String description, Boolean isDone, LocalDateTime createDateTime, LocalDateTime lastModified) {
        this.description = description;
        this.isDone = isDone;
        this.taskType = PossibleTaskTypes.DEFAULT;
        this.createDateTime = createDateTime;
        this.lastModifiedDateTime = lastModified;
    }

    /**
     * Returns the Task description String.
     *
     * @return String containing the task description.
     */
    public String getTaskName() {
        assert !this.description.isEmpty() : "Empty description should be handled by Duke Exception during input";
        return this.description;
    }

    /**
     * Returns a String, which is a tick if isDone is true, or a cross, if isDone is false.
     *
     * @return String representation of isDone.
     */
    public String getStatusIcon() {

        if (this.isDone) {
            return ("✓");
        } else {
            return ("✘");
        }

        // return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Setter function for setting a Task isDone from false to true.
     *
     * @param stats Statistic object.
     */
    public void setDone(Statistic stats) {
        if (!this.isDone) {
            this.isDone = true;
            updateLastModified();
        }
        return;
    }

    /**
     * Returns a parsed String of the Task object, depending on the subclass implementation.
     * Eg. [T][✘] (Task Description).
     *
     * @return Parsed string.
     */
    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.description);
    }

    /**
     * Returns a parsed String, meant for saving, of the Task object.
     *
     * @return Parsed string for saving.
     */
    public String toSaveString() {
        int booleanNum = (this.isDone ? 1 : 0);

        return (" | " + booleanNum + " | " + this.description);
    }

    /**
     * Returns the state of a Task, if it is a Default type or not.
     *
     * @return Boolean status if TaskType is default.
     */
    public Boolean isDefault() {
        return this.taskType.equals(PossibleTaskTypes.DEFAULT);
    }

    /**
     * Returns the state of a Task, if it is a Default type or not.
     *
     * @return Boolean status if TaskType is default.
     */
    public Boolean isTodo() {
        return this.taskType.equals(PossibleTaskTypes.TODO);

    }

    /**
     * Returns the state of a Task, if it is a Event type or not.
     *
     * @return Boolean status if TaskType is Event.
     */
    public Boolean isEvent() {
        return this.taskType.equals(PossibleTaskTypes.EVENT);


    }

    /**
     * Returns the state of a Task, if it is a Deadline type or not.
     *
     * @return Boolean status if TaskType is deadline.
     */
    public Boolean isDeadline() {
        return this.taskType.equals(PossibleTaskTypes.DEADLINE);
    }

    /**
     * An enumeration of the possible task types.
     */
    enum PossibleTaskTypes {
        DEFAULT, DEADLINE, EVENT, TODO
    }

    /**
     * Getter function to obtain Boolean status of task.
     *
     * @return isDone attribute.
     */
    public Boolean isDone() {

        return this.isDone;
    }

    /**
     * Getter function for createDateTime.
     *
     * @return Returns createDAteTime.
     */
    public LocalDateTime getCreateDateTime() {
        return this.createDateTime;
    }

    /**
     * Getter function for lastModifiedDateTime.
     *
     * @return lastModifiedDateTime.
     */
    public LocalDateTime getLastModifiedDateTime() {
        return this.lastModifiedDateTime;
    }

    /**
     * Updates lastModifiedDateTime.
     */
    public void updateLastModified() {

        this.lastModifiedDateTime = LocalDateTime.now();
    }

    /**
     * Returns the char type.
     *
     * @return Char representing the taskType.
     */
    public abstract char getTaskType();
}

