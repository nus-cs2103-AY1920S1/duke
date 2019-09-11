package duke.command;

import duke.todo.Task;
import duke.todo.TaskList;
import duke.ui.Ui;

/**
 * Class for TodoCommand.
 */
public class TodoCommand implements Command {
    private String task;
    private String duration;

    /**
     * Constructs a todo command based on the description.
     *
     * @param task Description of this todo.
     */
    public TodoCommand(String task) {
        this.task = task;
    }

    public TodoCommand(String task, String duration) {
        this.task = task;
        this.duration = duration;
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

    public String getDuration() {
        return duration;
    }

    public boolean hasFixedDuration() {
        return duration != null;
    }

    /**
     * Returns the description of this todo.
     *
     * @return Description of this todo.
     */
    public String getDescription() {
        return task;
    }

    public String getDate() {
        return "error";
    }

    public String getKeyword() {
        return "error";
    }

    public void execute(TaskList tasks, Ui ui) {
        Task addedTodo;
        if (duration != null) {
            addedTodo = tasks.addTask(getDescription(), duration);
            ui.reportAdd(addedTodo, tasks.getNumOfTasks());
        } else {
            addedTodo = tasks.addTask(getDescription());
            ui.reportAdd(addedTodo, tasks.getNumOfTasks());
        }
    }
}
