public class Task {
    protected String desc;
    protected boolean done;
    
    //@@author Parcly-Taxel
    /**
     * Initialises a Task not yet done with the given description.
     */
    public Task(String desc) {
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
    public String getDoneChar() {
        return (done ? "\u2713" : "\u2718");
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
