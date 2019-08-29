public class Event extends Task {

    public String at;

    public Event(String item, String at) {
        super(item);
        this.at = at;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[E][" + "\u2713" + "] " + super.toString() + "(at:" + at + ")";
        } else {
            return "[E][" + "\u2718" + "] " + super.toString() + "(at:" + at + ")";
        }
    }

}
