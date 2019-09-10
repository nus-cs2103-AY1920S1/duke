package duke.task;

import java.util.ArrayList;

/**
 * An object representation of a user task with a specified description.
 */
public class TodoTask extends Task {
    /**
     * Constructs a <code>TodoTask</code> object with a given description.
     * 
     * @param description <code>String</code> description of this <code>Task</code>.
     * @param tags an <code>ArrayList</code> of <code>String</code> tags for this task.
     */
    public TodoTask(String description, ArrayList<String> tags) {
        super(description, tags);
    }

    /**
     * {@inheritDoc}
     */
    public String toEncodedString() {
        return String.format(
            "T | %d | %s | %s",
            this.isComplete ? 1 : 0,
            this.tagsToString(),
            this.description
        );
    }

    @Override
    public String toString() {
        // Adds the type of the Task to the base toString() representation
        return String.format("[T]%s", super.toString());
    }
}
