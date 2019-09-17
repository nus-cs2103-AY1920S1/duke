package seedu.duke.task;

import seedu.duke.parser.DateParser;

import java.util.Date;

public class Event extends Task {
    protected String strAt;
    protected Date at;

    /** Constructs an Event object. Takes in the description of the task and the date of the event
     * as a string.
     * @param description Represents the description of the event.
     * @param strAt Represents the date of the event.
     */
    public Event(String description, String strAt) {
        super(description);
        this.strAt = strAt;
        this.at = new DateParser().understandDate(strAt);
    }

    @Override
    public String writeToFile() {
        return "E | " + (isDone ? "1" : "0") + " | " + this.description + " | " + this.strAt +
                ((priority == null)? "\n" : " | " + priority + "\n");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + strAt + ")" + ((priority == null)? "" : " <<P: " + priority
                + ">>");
    }
}
