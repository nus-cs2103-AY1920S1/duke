public class ToDoTask extends Task {
    private static final String DEFAULT_TODO_ICON = "[T]";
    public ToDoTask(String taskName) {
        super(taskName, DEFAULT_TODO_ICON);
        System.out.println("  " + this);
    }
}
