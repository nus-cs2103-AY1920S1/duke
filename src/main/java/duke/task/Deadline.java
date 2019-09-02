package duke.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents deadline task.
 * The 'Deadline' class supports operator (i) toString that
 * returns the string representation of task.
 */
public class Deadline extends Task {

    /**
     * Date the task is due
     */
    private Date deadline;


    /**
     * Initialises a new instance od Deadline task.
     * Used for when user inputs deadline to the chatbot.
     *
     * @param description Description of task
     * @param deadline    Date the event happens
     */
    public Deadline(String description, Date deadline) {
        super(description);
        this.isDone = false;
        this.deadline = deadline;

    }

    /**
     * Initialises a new instance od Deadline task.
     * Used for loading the tasks form text file to TaskList
     *
     * @param isDone      0 or 1 representation of whether a task is done
     * @param description Description of task
     * @param deadline    Date the task is due on
     */
    public Deadline(String isDone, String description, String deadline) {
        super(isDone, description);
        try {
            this.deadline = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy").parse(deadline.trim());


        } catch (ParseException e) {
            System.out.println(e.getMessage());

        }

    }

    /**
     * Returns a String representation of task.
     */
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + getDescription() + " (by: " + this.deadline + ")\n";
    }
}

