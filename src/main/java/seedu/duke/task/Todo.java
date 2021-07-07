package seedu.duke.task;

import seedu.duke.core.DukeException;
import seedu.duke.statistic.Statistic;
import java.time.LocalDateTime;

/**
 * Todo class is a subclass of Task class.
 * Has no additional attributes.
 */
public class Todo extends Task {

    /**
     * Returns a Todo object after initializing with the String description.
     *
     * @param description Description String ot the task.
     */
    public Todo(String description) throws DukeException {
        super(description);
        taskType = PossibleTaskTypes.TODO;
    }

    /**
     * Returns a Todo object after initializing with the String description and Boolean status of the task.
     *
     * @param description Description String ot the task.
     * @param isDone isDone status of the task.
     * @param dateCreateDateTime LocalDateTime object.
     * @param lastModifiedDateTime LocalDateTime object.
     */
    public Todo(String description, Boolean isDone, LocalDateTime dateCreateDateTime,
                LocalDateTime lastModifiedDateTime) {

        super(description, isDone, dateCreateDateTime, lastModifiedDateTime);
    }

    /**
     * Returns a parsed String of the Todo object.
     * Eg. description = "(Task Description)", isDone = false.
     * Parsed string = "[T][✘] (Task Description)".
     *
     * @return Parsed String of the Todo object.
     */
    @Override
    public String toString() {
        assert !this.description.isEmpty() : "Empty description should be handled by Duke Exception during input";
        return "[T]" + super.toString();
    }

    /**
     * Returns a parsed String, meant for saving, of the Todo object.
     * Eg. description = "(Task Description)", isDone = true.
     * Parsed saved string = "T | 1 | (Task Description)".
     *
     * @return Parsed saved string of the Todo object.
     */
    @Override
    public String toSaveString() {
        return ("T" + super.toSaveString() + " | " + " dummyExtraDescriptionForTodo"
                + " | " + this.getCreateDateTime().toString() + " | " + this.getLastModifiedDateTime().toString());
    }

    /**
     * Setter function for isDone Boolean var.
     * @param stats Statistic object.
     */
    @Override
    public void setDone(Statistic stats) {
        super.setDone(stats);
        stats.incrementTotalTodosCompleted();
    }

    /**
     * Returns the char type.
     *
     * @return Char representing the taskType.
     */
    public char getTaskType() {
        return 'T';
    }
}

