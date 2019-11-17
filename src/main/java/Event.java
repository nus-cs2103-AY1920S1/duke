public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Returns the format of the String to be saved in txt file
     */
    public String taskSavedTextFormat() {
        String done = "0";
        if (super.isDone) {
            done = "1";
        }
        return "E | " + done + " | " + super.description + " | " + at;
    }
}
