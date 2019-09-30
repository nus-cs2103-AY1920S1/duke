package duke.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A representation of the event task.
 */
public class Event extends Task {
    private Date atDate;

    /**
     * Public constructor for the Event class.
     * Creates an incomplete event task.
     *
     * @param description Description of the task.
     * @param at The start date and time of the task.
     * @throws ParseException If by is not of "dd/MM/yyyy HHmm" format.
     */
    public Event(String description, String at) throws ParseException {
        super(description);

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HHmm");
        this.atDate = format.parse(at);
    }

    /**
     * Converts information about the event task to String format (for addition to the data file).
     *
     * @return Task information in String format.
     */
    @Override
    public String printToTxtFile() {
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy HHmm");
        return String.format("E | %d | %s | %s", isDone ? 1 : 0, description, date.format(atDate));
    }

    /**
     * Converts information about the event task to String format (for printing to the UI).
     *
     * @return Task information in String format.
     */
    @Override
    public String toString() {
        SimpleDateFormat date = new SimpleDateFormat("E, dd/MM/yyyy HH:mm");
        return String.format("[E]%s (at: %s)", super.toString(), date.format(atDate));
    }
}
