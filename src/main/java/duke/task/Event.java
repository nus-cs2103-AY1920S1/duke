package duke.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents an event task.
 * The 'Deadline' class supports operators (i) toString that
 * returns the string representation of task.
 */
public class Event extends Task {

    /**
     * Date the event happens.
     */
    private Date time;


    /**
     * Initialises a new instance od Event task.
     * Used for when user inputs event task directly to the chatbot.
     *
     * @param description Description of task
     * @param time        Date the event happens
     */
    public Event(String description, Date time) {
        super(description);
        this.isDone = false;
        this.time = time;
    }

    /**
     * Initialises a new instance od Event task.
     * Used for loading the tasks form text file to TaskList.
     *
     * @param isDone      0 or 1 representation of whether a task is done
     * @param description Description of task
     * @param time        Date the task is due on
     */
    public Event(String isDone, String description, String time) {
        super(isDone, description);
        try {
            this.time = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy").parse(time.trim());


        } catch (ParseException e) {
            System.out.println(e.getMessage());

        }

    }


    /**
     * Returns a String representation of task.
     *
     * @return String representation of exception.
     */
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + getDescription() + " (at: " + this.time + ")\n";
    }
}
