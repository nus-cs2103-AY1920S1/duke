package duke.data.tasks;

import java.util.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Implements a deadline task.
 * @author Lim Yong Shen, Kevin
 */
public class Deadline extends Task {

    private Date dateAndTime;
    private SimpleDateFormat dateFormat;

    /**
     * Constructs a deadline task with the specified description.
     * @param description The specified description.
     */
    public Deadline(String description, String dateAndTime) throws ParseException {
        super(description);
        dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        dateFormat.setLenient(false);
        this.dateAndTime = dateFormat.parse(dateAndTime);
    }

    /**
     * Returns the command String (user input) used to create this deadline task.
     * @return The command String (user input) used to create this deadline task.
     */
    @Override
    public String getCommandString() {
        return String.format("deadline %s /by %s", description, dateFormat.format(dateAndTime));
    }

    /**
     * Returns the String representation of this deadline task.
     * @return The String representation of this deadline task.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), dateFormat.format(dateAndTime));
    }

}
