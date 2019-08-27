public class ToDoTask extends Task {
    private static final String DEFAULT_TODO_ICON = "[T]";
    private static final String TODO_TASK_TYPE = "Todo";

    /**
     * Constructor
     * @param taskName - Name of given task
     */
    public ToDoTask(String taskName) {
        super(taskName, DEFAULT_TODO_ICON,TODO_TASK_TYPE);
    }
}
