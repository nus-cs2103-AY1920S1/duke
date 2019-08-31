package task;

import tool.DateTime;

public class Deadline extends Task {
    protected DateTime by;

    public Deadline(String description, DateTime by) {
        super(description);
        this.by = by;
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


