public class Event extends Task{
    protected String atDateTime;

    public Event(String description, String atDateTime) {
        super(description);
        this.atDateTime = atDateTime;
    }

    public String getAtDateTime() {
        return atDateTime;
    }


    @Override
    public String toString() {
        String str = "["
                + "E"
                + "]["
                + this.getStatusIcon()
                + "] "
                + this.getDescription()
                + "(by: "
                + this.getAtDateTime()
                + ")";
        return str;
    }
}