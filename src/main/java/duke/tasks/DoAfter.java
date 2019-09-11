package duke.tasks;

/**
 * DoAfter class that can be created by the user. Handles all Duke.tasks with doafter tasks
 */
public class DoAfter extends Task {
    
    protected String after;
    
    /**
     * Class constructor.
     *
     * @param description details of the respective task.
     * @param after          deadline date
     */
    public DoAfter(String description, String after) {
        super(description);
        this.after = after;
    }
    
    /**
     * Prints the deadline with the status, the description and the deadline date together.
     */
    @Override
    public String toString() {
        return "[A]" + super.toString() + " after " + this.after;
    }
}