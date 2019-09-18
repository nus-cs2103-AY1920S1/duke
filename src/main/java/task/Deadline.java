package task;

import tool.DateTime;

public class Deadline extends Task {
    protected DateTime by;

    public Deadline(String description, DateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Edits the specified attribute of task object with given update.
     * @param attribute: done, des, date
     * @param update: true/false, new des, date
     */
    @Override
    public void edit(String attribute, String update) {
        if (attribute.equals("done")) {
            assert update.equals("true") || update.equals("false") : "Must provide true or false only";
            this.isDone = update.equals("true");
        } else if (attribute.equals("des")) {
            this.description = update;
        } else if (attribute.equals("date")) {
            this.by = new DateTime(update);
        } else {
            System.out.println("Attribute does not exist");
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


