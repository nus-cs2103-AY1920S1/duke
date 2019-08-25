/** A class representing a event task. */
public class Event extends Task {
    private String time;

    /**
     * Constructor for the event task.
     * @param description the description of the event.
     * @param time the time at which the event starts.
     */
    public Event(String description, String time) throws IllegalDescriptionException {
        super(description);
        if (time.isEmpty()) {
            throw new IllegalDescriptionException("The time cannot be empty.");
        }
        this.time = time;
    }

    /**
     * Returns a string representation of the task to be stored in file.
     * @return a string representation of the task to be stored in file, consisting of the task type,
     *         status, description and time.
     */
    public String toStringForFile() {
        return super.toStringForFile() + " | " + time;
    }

    /**
     * Returns a string representaion of the task.
     * @return a string representation of the task consisting of the task type,
     *         status, description and time.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
