package dukepkg;
public class Todo extends dukepkg.Task {
    public static final String type = "T";
    public Todo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
