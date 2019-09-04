package duke.task;

public class Deadline extends Task {

    public Deadline(String description, String extraInfo) {
        super(description);
        this.extraInfo = extraInfo;
        this.type = "deadline";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.extraInfo + ")";
    }
}
