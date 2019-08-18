package duke.task;

/**
 * A generic task, which can be marked as done.
 */
public class Task {
    private final String desc;
    private boolean done;

    /**
     * Initializes a task not yet done with the given description.
     *
     * @param desc A description of this task.
     */
    Task(String desc) {
        this.desc = desc;
        this.done = false;
    }
    
    /**
     * Marks this task as done.
     */
    void markDone() {
        done = true;
    }
    
    /**
     * Gets a Unicode character representing whether this task is done
     * (tick for yes, cross for no).
     */
    private String getDoneChar() {
        return done ? "\u2713" : "\u2718";
    }
    
    /**
     * Returns a string representation of this task.
     *
     * @return The desired string representation.
     */
    @Override
    public String toString() {
        String boxedChar = "[" + getDoneChar() + "]";
        return boxedChar + " " + desc;
    }

    /**
     * Exports this task in a short, ASCII-friendly format. Suitable for interchange
     * with other applications or just viewing bare.
     *
     * @return A string representation of this task containing its done status (0 or 1)
     * and its description.
     */
    public String export() {
        return (done ? "1|" : "0|") + desc;
    }
}
