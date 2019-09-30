package task;

import tool.DateTime;
import tool.DukeException;

public class Event extends Task {
    private DateTime start;
    private DateTime end;

    /**
     * Constructor for Event task
     * @param description: Task description
     * @param start: starting DateTime object for Event task
     * @param end: ending DateTime object for Event task
     */
    public Event(String description, DateTime start, DateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Edits the specified attribute of task object with given update.
     * @param attribute: Attribute to be edited: done, des, date
     * @param update: New attribute: true/false, new des, date
     */
    @Override
    public void edit(String attribute, String update) throws DukeException {
        switch (attribute) {
            case "done":
                assert update.equals("true") || update.equals("false") : "Must provide true or false only";
                this.isDone = update.equals("true");
                break;
            case "des":
                this.description = update;
                break;
            case "date":
                String[] dates = update.split(" to ");
                this.start = new DateTime(dates[0]);
                this.end = new DateTime(dates[1]);
                break;
            default:
                throw new DukeException("Attribute does not exist. Attributes are des and date only.");
        }
    }

    /**
     * Formats the string to how it should be saved in the .txt file
     * @return String to save in the .txt file
     */
    @Override
    public String storageString() {
        return "E/" + status + "/" + description + "/" + this.start + "/" + this.end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.start + " to " + this.end + ")";
    }
}
