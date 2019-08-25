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
     * Formats the task in suitable String representation for
     * writing to file.
     */
    @Override
    public String formatForFile() {
        String formattedTask = "D | ";

        if(isDone) {
            formattedTask += "1 | ";
        } else {
            formattedTask += "0 | ";
        }

        formattedTask += description + " | " + dateTime;

        return formattedTask;
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
