package seedu.duke.model.task;

/**
 * Defines a Todo object that represents a todo task.
 * Inherits attributes and methods from Task class.
 */
public class Todo extends Task {

    /**
     * Creates a Todo object with a task description.
     * @param taskString Task description of the todo.
     */
    public Todo(String taskString) {
        super(Type.TODO, taskString, null);
        assert taskString != null : "Task does not have a task description";
        assert this.getDateAndTime() == null : "Task of type Todo has a DateAndTime attribute that "
                + "violates the intended properties of a Todo object.";
    }

    /**
     * Creates a Todo object with a task description and isDone state.
     * Allows a client of this client to define whether this Todo has been completed.
     * @param taskString Task description of the Todo.
     * @param isDone A boolean that denotes whether or not the Todo to be created has already been completed.
     */
    public Todo(String taskString, boolean isDone) {
        this(taskString);
        assert taskString != null : "Task does not have a task description";
        this.setIsDone(isDone);
        assert this.getIsDone() == isDone : "Done status of Task differs from status intended to be set.";
        assert this.getDateAndTime() == null : "Task of type Todo has a DateAndTime attribute that "
                + "violates the intended properties of a Todo object.";
    }
}
