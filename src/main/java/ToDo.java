/**
 * Simulates a to do task for accessing and updating descriptions
 * and done statuses.
 * @author Fabian Chia Hup Peng
 */

public class ToDo extends Task {

    /**
     * Creates a ToDo instance with the appropriate attributes.
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Formats the task in suitable String representation for
     * writing to file.
     */
    @Override
    public String formatForFile() {
        String formattedTask = "T | ";

        if(isDone) {
            formattedTask += "1 | ";
        } else {
            formattedTask += "0 | ";
        }

        formattedTask += description;

        return formattedTask;
    }

    /**
     * Returns a string representation of a ToDo task.
     * @return The task's status icon and description.
     */
    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + this.description;
    }

}
