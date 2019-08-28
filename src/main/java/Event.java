import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    String eventDate;


    /**
     * The constructor for event task
     * @param taskName the task name
     * @param eventDate the event date
     */
    Event(String taskName, String eventDate) {
        super(taskName);
        this.eventDate = eventDate;

    }

    /**
     * getter for the event date
     * @return the event date in string format
     */
    public String getEventDate() {
        return this.eventDate;
    }

    /**
     * get the string representation of the task
     * @return the task details in a String
     */
    public String getTaskDetails() {
        String doneSymbol;
        if (isDone()) {
            doneSymbol = "✓";
        } else {
            doneSymbol = "✗";
        }
        return "[E]" + "[" + doneSymbol + "] " + getTaskName() + " (at: " + eventDate + ")";
    }
}

