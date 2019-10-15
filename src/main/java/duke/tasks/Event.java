package duke.tasks;


/**
 * Represents an event task in the application.
 * An event provides the getter methods to its date.
 */
public class Event extends Task {

    private String date;

    /**
     * Initialises an deadline task with the description and date and time of the event task.
     *
     * @param description Event description
     * @param date Date description
     */
    public Event(String description, String date) {
        super(description, TaskType.EVENT_TASK);
        this.date = date;
        assert date != null;
    }

    /**
     * Returns a string containing the date and time of a event task
     *
     * @return String containing the date and time of a event task.
     */
    public String getDate() {
        assert date != null;
        return this.date;
    }

    /**
     * Returns A string that includes the task type, description and date of the event task.
     *
     * @return String that includes the task type, description and date of the event task.
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s(at: %s)", getStatusIcon(),
                getDescription(), getDate());
    }
}