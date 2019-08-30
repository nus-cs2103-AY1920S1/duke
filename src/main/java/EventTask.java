import java.time.LocalDateTime;

/**
 * Represents an event to attend that contains both a description and event time.
 */
public class EventTask extends Task {
    /**
     * The time of the EventTask, as inputted by the user.
     */
    protected LocalDateTime time;
    
    /**
     * Creates an EventTask.
     *
     * @param description The description of the EventTask, as inputted by the user.
     * @param time The time of the EventTask, as inputted by the user.
     */
    public EventTask(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }
    
    /**
     * Returns a default formatted String for writing data to the user's hard drive.
     * EventTask String includes tag identifying this as an EventTask, in addition to the Task default
     * formatted String.
     * Also includes the value of the deadline's time, formatted as a String.
     *
     * @return Returns a String.
     */
    @Override
    public String formattedString() {
        return "E | " + super.formattedString() + " | " + time.getDayOfMonth() + "/" + time.getMonthValue() + "/"
                + String.format("%04d", time.getYear()) + " " + String.format("%02d", time.getHour())
                + String.format("%02d", time.getMinute()) + "\n";
    }
    
    /**
     * Returns a default String for printing to the user's console.
     * EventTask String includes tag identifying this as an EventTask, in addition to the Task default String.
     * Also includes the value of the event's time, formatted as a String.
     *
     * @return Returns a String.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time.getDayOfMonth() + " of " + time.getMonth() + ", "
                + time.getYear() + ", at " + String.format("%02d", time.getHour())
                + String.format("%02d", time.getMinute()) + " hrs)";
    }
}
