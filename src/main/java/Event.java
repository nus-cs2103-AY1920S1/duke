public class Event extends Task {

    // the Event subclass adds one field
    public String at;

    // the Event subclass has one constructor
    public Event(String item, String at) {
        super(item);
        this.at = at;
    }

    @Override
    // overrides toString method in Task
    public String toString() {
        if (isDone) {
            return "[E][" + "\u2713" + "] " + super.toString() + "(at:" + at + ")";
        } else {
            return "[E][" + "\u2718" + "] " + super.toString() + "(at:" + at + ")";
        }
    }

}
