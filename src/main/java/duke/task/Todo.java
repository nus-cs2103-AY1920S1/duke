package duke.task;

import duke.exception.NullDateException;
import duke.formatter.DateFormatter;

import java.util.Date;

/**
 * A todo item is a task with description. The todo item can be created, marked as done or deleted.
 */
public class Todo extends Task {

    /**
     * Constructs a new todo task with description that has not been done.
     * @param description the description of the todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a new todo task with description and the option to mark it as done.
     * @param description the description of the todo task
     * @param isDone the done status of the task
     */
    public Todo(String description, boolean isDone) {
        super(description);
        if(isDone) {
            this.markAsDone();
        }
    }

    /**
     * Encodes the todo task to be stored in the storage.
     * @return a string representation of the encoded todo task
     */
    public String encode() {
        return "todo," + super.encode();
    }

    /**
     * Returns a string representation of the todo task. The string representation consist of ("[T]") with the string
     * representation of the task.
     * @return a string representation of the todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
