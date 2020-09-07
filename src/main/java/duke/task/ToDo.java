package duke.task;

/**
 * Class for To Do tasks.
 */
public class ToDo extends Task {
    /**
     * Constructor for To Do task. Sets description.
     *
     * @param description description of task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns String in text file format.
     *
     * @return a String to write on text file
     */
    public String toFile() {
        return "T|" + getStatusIcon() + "|" + description;
    }

    /**
     * Returns String to output on terminal.
     *
     * @return a String to output on terminal
     */
    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + description;
    }
}
