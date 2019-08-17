package task;

/**
 * A generic task, which can be marked as done.
 */
public class Task {
    private final String desc;
    private boolean done;

    /**
     * Initialises a Task not yet done with the given description.
     *
     * @param desc A description of this task.
     */
    Task(String desc) {
        this.desc = desc;
        this.done = false;
    }
    
    /**
     * Marks this Task as done.
     */
    void markDone() {
        done = true;
    }
    
    /**
     * Gets a Unicode character representing whether this Task is done
     * (tick for yes, cross for no).
     */
    private String getDoneChar() {
        return done ? "\u2713" : "\u2718";
    }
    
    /**
     * Returns a string representation of this Task.
     *
     * @return The desired string representation.
     */
    @Override
    public String toString() {
        String boxedChar = "[" + getDoneChar() + "]";
        return boxedChar + " " + desc;
    }
}
