package task;

import tool.DateTime;

public class Event extends Task {
    protected DateTime at;

    /**
     * Constructor for Event task
     * @param description: Task description
     * @param at: DateTime object for Event task
     */
    public Event(String description, DateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Edits the specified attribute of task object with given update.
     * @param attribute: Attribute to be edited: done, des, date
     * @param update: New attribute: true/false, new des, date
     */
    @Override
    public void edit(String attribute, String update) {
        switch (attribute) {
            case "done":
                assert update.equals("true") || update.equals("false") : "Must provide true or false only";
                this.isDone = update.equals("true");
                break;
            case "des":
                this.description = update;
                break;
            case "date":
                this.at = new DateTime(update);
                break;
            default:
                System.out.println("Attribute does not exist");
                break;
        }
    }

    /**
     * Formats the string to how it should be saved in the .txt file
     * @return String to save in the .txt file
     */
    @Override
    public String storageString() {
        return "E/" + status + "/" + description + "/" + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
