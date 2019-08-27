package dukepkg;
public class Todo extends dukepkg.Task {
    public Todo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
