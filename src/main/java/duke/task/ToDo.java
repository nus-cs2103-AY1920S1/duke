package duke.task;

import java.util.function.Function;

/** Represents a to do task. */
public class ToDo extends Task {

    /**
     * Creates a {@link Todo} task that contains a description.
     *
     * @param description Description of the to do task
     */
    public ToDo(String description) {
        super(description);
    }


    /**
     * Returns a string formatted to style suitable for {@link duke.util.Storage}.
     *
     * @return String formatted for {@link duke.util.Storage}.
     */
    @Override
    public String getStorageSyntax() {
        int isDoneInteger = super.isDone ? 1 : 0;
        return String.format("T | %d | %s | %d", isDoneInteger, this.description, this.priority);
    }

    /**
     * Returns a description of the properties of the task.
     *
     * @return a string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
