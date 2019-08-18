package duke.task;

/**
 * A generic task, which can be marked as done.
 */
public class Task {
    private final String desc;
    protected boolean done;

    /**
     * Initializes a task not yet done with the given description.
     *
     * @param desc A description of this task.
     */
    public Task(String desc) {
        this.desc = desc;
        this.done = false;
    }
    
    /**
     * Marks this task as done.
     */
    public void markDone() {
        done = true;
    }
    
    /**
     * Gets a Unicode character representing whether this task is done.
     *
     * @return U+2713 (tick) if this task is done, U+2718 (cross) otherwise.
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
