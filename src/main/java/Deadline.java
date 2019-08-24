/**
 * Simulates a deadline task for accessing and updating descriptions
 * and done statuses.
 * @author Fabian Chia Hup Peng
 */

public class Deadline extends Task {

    private String dateTime;

    /**
     * Creates a Deadline instance with the appropriate attributes.
     * @param description The description of the task.
     */
    public Deadline(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Returns a string representation of a deadline task.
     * @return The task's status icon and description.
     */
    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + this.description + " (" +
                    this.dateTime + ")";
    }

}
