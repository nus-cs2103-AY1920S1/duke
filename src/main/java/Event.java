public class Event extends Task {
    protected String duration;

    Event(String desc) {
        super(desc);
        this.duration = "no idea :-p";
    }
    Event(String desc, boolean done) {
        super(desc, done);
        this.duration = "no idea :-p";
    }
    Event(String desc, String duration) {
        super(desc);
        this.duration = duration;
    }
    Event(String desc, String duration, boolean done) {
        super(desc, done);
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getDuration() + ")";
    }
}
