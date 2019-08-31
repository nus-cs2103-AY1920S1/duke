package duke.task;

public class Todo extends Task {

    public Todo(String desc) {
        super(desc);
    }

    public Todo(String desc, boolean done) {
        super(desc, done);
    }

    public static Task parse(String commandContent) {
        return new Todo(commandContent);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
