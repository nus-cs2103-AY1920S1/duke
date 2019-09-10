package seedu.duke.task;

import java.time.LocalDate;

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
    public Todo(String description) {
        super(description);
        taskType = PossibleTaskTypes.TODO;
    }

    /**
     * Returns a Todo object after initializing with the String description and Boolean status of the task.
     *
     * @param description Description String ot the task.
     * @param isDone isDone status of the task.
     */
    public Todo(String description, Boolean isDone, LocalDate dateCreated, LocalDate lastModified) {

        super(description, isDone, dateCreated, lastModified);
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
        + " | " + this.getCreatedDate().toString() + " | " + this.getLastModifiedDate().toString());
    }
}

