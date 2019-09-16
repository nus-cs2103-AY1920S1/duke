package duke.task;

public class Event extends Task {

    // the Event subclass adds one field
    private String at;

    // the Event subclass has one constructor
    public Event(String item, String at) {
        super(item);
        this.at = at;
    }

    @Override
    // overrides toString method in Task
    public String toString() {
        if (isDone) {
            return "[E][" + "/" + "] " + super.toString() + "(at:" + at + ")";
        } else {
            return "[E][" + "x" + "] " + super.toString() + "(at:" + at + ")";
        }
    }

    @Override
    // overrides saveTask method in Task
    public String saveTask() {
        if (isDone) {
            return "E" + " | " + "1" + " | " + super.toString() + " | " + at + "\n";
        } else {
            return "E" + " | " + "0" + " | " + super.toString() + " | " + at + "\n";
        }
    }

}
