package duke.initials;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string that represents deadline that can be input into the txt file.
     * This method transforms the task into the specific format for the txt file
     * @return the data for this task
     */
    public String getData() {
        return "D" + "|" +
                (isDone ? "1" : "0") + "| " + description + "| " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}
