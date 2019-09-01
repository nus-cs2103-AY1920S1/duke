package seedu.duke.task;

public class Todo extends Task {

    public Todo(String task) {
        super(task);
    }

    public Todo(String task, String done) {
        super(task);
        if (done.equals("1")) {
            super.markAsDone();
        }
    }

    public String toStorageString() {
        String output = "T|";
        if (super.isDone) {
            output = output + "1|";
        } else {
            output = output + "0|";
        }
        output = output + super.taskName;
        return output;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
