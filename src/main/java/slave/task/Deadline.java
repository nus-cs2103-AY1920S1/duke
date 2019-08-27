package slave.task;

/**
 * Class representing a Deadline task
 */
public class Deadline extends Task {


    private String by;

    /**
     * Constructor for Deadline task
     * @param description description of deadline
     * @param id id of deadline task
     * @param by deadline date
     */
    public Deadline(String description, int id, String by) {
        super(description, id);
        this.by = by;
        this.type = TaskType.DEADLINE;
    }

    /**
     * getter for date of deadline task
     * @return date of deadline task
     */
    @Override
    public String getDate() {
        return this.by;
    }

    /**
     * Converts deadline task to an appropriate String representation
     * @return formatted string of deadline task to be printed
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
