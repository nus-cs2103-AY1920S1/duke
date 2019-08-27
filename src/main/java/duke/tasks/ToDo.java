package duke.tasks;

public class ToDo extends Task {

    public ToDo(String task) {
        super(task);
    }

    public ToDo(String task, Boolean done) {
        super(task, done);
    }

    @Override
    public String toString() {
        String iconForDone = done ? "\u2713" : "\u2718";
        return String.format("[T][%s] %s", iconForDone, this.task);
    }
}