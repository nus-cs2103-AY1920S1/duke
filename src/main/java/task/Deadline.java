package task;

import tool.DateTime;
import tool.DukeException;

public class Deadline extends Task {
    private DateTime by;

    /**
     * Constructor for Deadline Task
     * @param description: Task description
     * @param by: DateTime object for Deadline Task
     */
    public Deadline(String description, DateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Edits the specified attribute of task object with given update.
     * @param attribute: Attribute to be edited: done, des, date, time
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
                this.by = new DateTime(update);
                break;
            default:
                throw new DukeException("Attribute does not exist");
        }
    }

    /**
     * Formats the string to how it should be saved in the .txt file
     * @return String to save in the .txt file
     */
    @Override
    public String storageString() {
        return "D/" + status + "/" + description + "/" + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

}


