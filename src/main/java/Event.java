/**
 * Simulates an event task for accessing and updating descriptions
 * and done statuses.
 * @author Fabian Chia Hup Peng
 */

public class Event extends Task {

    private String dateTime;

    /**
     * Creates an Event instance with the appropriate attributes.
     * @param description The description of the task.
     */
    public Event(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Formats the task in suitable String representation for
     * writing to file.
     */
    @Override
    public String formatForFile() {
        String formattedTask = "E | ";

        if(isDone) {
            formattedTask += "1 | ";
        } else {
            formattedTask += "0 | ";
        }

        formattedTask += description + " | " + dateTime;

        return formattedTask;
    }

    /**
     * Returns a string representation of an Event task.
     * @return The task's status icon and description.
     */
    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + this.description + " (" +
                this.dateTime + ")";
    }

}
