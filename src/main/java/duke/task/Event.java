package duke.task;

import duke.ui.DukeException;

import java.text.SimpleDateFormat;
import java.text.Format;
import java.text.ParseException;
import java.util.Date;

/**
 * Task type of Event.
 * Has the property of date and time.
 */
public class Event extends Task {
    protected String at;
    protected Date date;
    private SimpleDateFormat input = new SimpleDateFormat("dd/MM/yyyy HHmm");

    /**
     * Constructor for Event Task from user input.
     */
    public Event(String[] parsedTask) throws DukeException {
        super("Event", parsedTask[0]); //parent constructor
        this.at = parsedTask[1]; //without "/at "
        try {
            this.date = input.parse(at);
        } catch (ParseException pe) {
            throw new DukeException("Parsing error detected.");
        }
    }

    /**
     * Constructor for Event Task from hard drive storage.
     */
    public Event(boolean isDone, String task, String at) throws DukeException {
        super(isDone, "Event", task);
        this.at = at;
        try {
            this.date = input.parse(at);
        } catch (ParseException pe) {
            throw new DukeException("Parsing error detected.");
        }
    }

    /**
     * Prints the date in the format required.
     *
     * @return date in dd MMMM yyyy hh.mmaa format
     */
    public String printDate() {
        Format formatter = new SimpleDateFormat("dd MMMM yyyy hh.mmaa");
        String dateFormatted = formatter.format(this.date);
        return dateFormatted;
    }

    @Override
    public String saveTask() {
        return "E" + super.saveTask() + " | " + this.at;
    }

    @Override
    public String toString() {
        return " [E]" + super.toString() + " (at: " + this.printDate() + ")";
    }
}