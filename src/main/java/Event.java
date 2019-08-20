public class Event extends Task {

    private String when;

    public Event(String desc, String when) {
        super(desc);
        this.when = when;
    }

    public String getWhen() {
        return this.when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (at: %s)", "E", super.getDoneSymbol(), this.desc, this.when);
    }
}
