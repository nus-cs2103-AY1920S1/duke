package duke.dukeTask;

public class ToDo extends Task {

    public ToDo(String description, int isDone) {
        super(description, isDone);
        this.type = "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
