package duke.task;

import duke.exception.DukeException;
import duke.tag.Tag;

import java.util.HashSet;

/**
 * Represents a task to be done.
 */
public class Todo extends Task {
    /**
     * Initializes a Todo object with the description of the task.
     * This object is initialized as uncompleted.
     * @param description description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Initializes a Todo object with description and tags of the task.
     * @param description description of task.
     * @param tags tags tagged with the task.
     */
    public Todo(String description, HashSet<Tag> tags) {
        super(description, tags);
    }

    /**
     * Returns a string representation to be used for saving data of this object.
     * @return String representation of this object for saving.
     */
    @Override
    public String getSaveData() {
        return "T \0 " + super.getSaveData();
    }

    /**
     * Returns a string representation of this object.
     * @return String representation of this object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
