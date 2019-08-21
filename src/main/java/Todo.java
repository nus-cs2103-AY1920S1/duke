package DukePkg;
public class Todo extends DukePkg.Task {
    public Todo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
