package seedu.duke;

public class Event extends Task {

    protected String at;

    public Event(String task, String at) {
        super(task);
        this.at = at;
    }

    public Event(String task, String done, String at) {
        super(task);
        this.at = at;
        if (done.equals("1")) {
            super.markAsDone();
        }
    }

    public String toStorageString() {
        String output = "E|";
        if (super.isDone) {
            output = output + "1|";
        } else {
            output = output + "0|";
        }
        output = output + super.taskName + "|" + this.at;
        return output;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
