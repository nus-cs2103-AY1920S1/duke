package duke.task;

public class Todo extends Task {
    public Todo(String name) {
        super(name);
        //Format date;
    }

    @Override
    public String toString() {
        return String.format("[T]" + super.toString());
    }
}
