package duke.task;

import java.util.Optional;

public class ToDo extends Task {
    /**
     * Constructor.
     */
    public ToDo(String name) {
        super(name, Optional.empty(), TaskType.TODO, false);
    }

    /**
     * Constuctor with isDone.
     */
    public ToDo(String name, boolean isDone) {
        super(name, Optional.empty(), TaskType.TODO, isDone);
    }

    @Override
    /**
     * Overrides toString method.
     * @return String
     */
    public String toString() {
        return String.format("[T]%s %s", getStatus(), this.name);
    }
}
