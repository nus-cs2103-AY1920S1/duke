package duke.task;

import duke.command.Commands;

public class ToDoTask extends Task {
    private static final String DEFAULT_TODO_ICON = "[T]";
    private static final Commands TODO_TASK_TYPE = Commands.TODO;

    /**
     * Constructor.
     * @param taskName - Name of given task
     */
    public ToDoTask(String taskName) {
        super(taskName, DEFAULT_TODO_ICON,TODO_TASK_TYPE);
    }

    /**
     * Returns a copy of the existing task.
     * @return new task
     */
    public Task getTaskCopy() {
        Task task = new ToDoTask(this.getName());
        if (this.isDone()) {
            task.markDone();
        }
        return task;
    }
}
