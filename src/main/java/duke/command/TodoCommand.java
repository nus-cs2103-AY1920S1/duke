package duke.command;

public class TodoCommand implements Command {
    private String taskType = "todo";
    private String task;

    /**
     * Constructs a todo command based on the description.
     *
     * @param task Description of this todo.
     */
    public TodoCommand(String task) {
        this.task = task;
    }

    /**
     * Returns task type.
     *
     * @return Todo.
     */
    public String getTaskType() {
        return "todo";
    }

    public int getIndex() {
        return 0;
    }

    /**
     * Returns the description of this todo.
     *
     * @return Description of this todo.
     */
    public String getTask() {
        return task;
    }

    public String getDate() {
        return "error";
    }

    public String getKeyword() {
        return "error";
    }
}
