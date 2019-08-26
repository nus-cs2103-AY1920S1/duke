public class Event extends Task {

    protected String at;

    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String getDate() {
        return " (at: " + at + ")";
    }

    @Override
    public boolean getStatus() {
        return isDone;
    }

    public String getAt() {
        return at;
    }
}