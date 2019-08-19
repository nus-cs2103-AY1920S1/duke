public class Event extends Task {
    protected String duration;

    public Event(String description, String duration) {
        super(description);
        this.typeOfTask = "E";
        this.duration = duration;
    }

    @Override
    public String toString() {
        String[] prepositionSplit = duration.split(" ",2);
        String statusIcon = this.getStatusIcon();
        return ("[" + typeOfTask + "]" + "[" + statusIcon + "] "
                + description + "(" + prepositionSplit[0] + ": "
                + prepositionSplit[1] + ")");
    }
}
