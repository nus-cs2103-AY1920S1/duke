public class Event extends Task {
    protected DateTime at;

    public Event(String description, DateTime at) {
        super(description);
        this.at = at;
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
