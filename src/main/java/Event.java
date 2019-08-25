public class Event extends Task {
    private String time;

    public Event(String name, String time) {
        super(name, false);
        this.time = time;
    }

    public Event(String name, String deadLine, boolean done) {
        super(name, done);
        this.time = deadLine;
    }

    @Override
    public Event isDone() {
        return new Event(super.name, this.time, true);
    }

    @Override
    public String toString() {
        String s = "";
        if(done) {
            s = s + "[E][✓]";
        } else {
            s = s + "[E][✗]";
        }

        return s + " " + name + " (at: " + time + ")";
    }

    public String toIndicationInsideFile() {
        String s = "E | ";

        if(done) {
            s = s + "1 | ";
        } else {
            s = s + "0 | ";
        }

        return s + name + " | " + time;
    }
}