public class Todo extends Task {
    public Todo(String taskName, int index) {
        super(taskName, index);
        setType('T');
    }
}
