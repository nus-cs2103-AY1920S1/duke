/**
 * This is a class for event tasks.
 * @author Choong Yong Xin
 */

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {

    Date at;
    String atString;

    public Event(String description, String eventDate){
        super(description);
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
            this.at = dateFormat.parse(eventDate);
            this.atString = eventDate;
        } catch (Exception e){
            this.atString = eventDate;
        }
    }

    /**
     * Returns string for task display.
     *
     * @return Display string
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + atString + ")";
    }

    /**
     * Returns string for file writing.
     *
     * @return String to be saved.
     */
    @Override
    public String stringForAppend() {
        return "E | " + super.getStatusIcon() + " | " + description + " | " + atString;
    }
}