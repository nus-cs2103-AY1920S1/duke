package task;

public class Task {
    private final String desc;
    private boolean done;
    
    //@@author Parcly-Taxel
    /**
     * Initialises a Task not yet done with the given description.
     */
    Task(String desc) {
        this.desc = desc;
        this.done = false;
    }
    
    /**
     * Marks this Task as done.
     */
    public void markDone() {
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
     */
    @Override
    public String toString() {
        String boxedChar = "[" + getDoneChar() + "]";
        return boxedChar + " " + desc;
    }
}
