package duke.initials;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns a string that represents deadline that can be input into the txt file.
     * This method transforms the task into the specific format for the txt file
     * @return the data for this task
     */
    public String getData() {
        return "E" + "|" +
                (isDone ? "1" : "0") + "| " + description + "| " + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at + ")";
    }
}
