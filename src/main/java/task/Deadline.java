package task;

/**
 * Tasks.Deadline class which inherits from the Tasks.Task class and is used to represent
 * deadlines within Core.Duke.
 */

public class Deadline extends Task {

    String dueDate;

    /**
     * Returns a new Tasks.Deadline object for use within Core.Duke.
     * @param task Description of the deadline task.
     * @param dueDate Description of the time the deadline needs to be finished.
     */
    public Deadline(String task, String dueDate) {
        super(task);
        this.dueDate = dueDate;
    }

    /**
     * Returns a new Tasks.Deadline object for use within Core.Duke with a predermined completion state.
     * @param task Description of the deadline task.
     * @param dueDate Description of the time the deadline needs to be finished.
     * @param complete Boolean variable to determine if the deadline has been finished or not.
     */
    public Deadline(String task, String dueDate, boolean complete) {
        super(task, complete);
        this.dueDate = dueDate;
    }
    
    /**
     * Returns the String representation of a deadline as it is stored in text file on the local system.
     */
    @Override
    public String toStringForFile() {
        String isComplete = this.complete ? "1" : "0";
        return "D | " + isComplete + " | " + task + " | " + dueDate; 
    }

    /**
     * Returns the String representation of a deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate + ")";
    }

}