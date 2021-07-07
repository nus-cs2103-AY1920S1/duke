package duke.command;

import duke.todo.Task;
import duke.todo.TaskList;
import duke.ui.Ui;

/**
 * Class for delete command.
 */
public class DeleteCommand implements Command {
    private int index;

    /**
     * Generates delete command based on the index.
     *
     * @param index Index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Return task type.
     *
     * @return Delete.
     */
    public String getTaskType() {
        return "delete";
    }

    /**
     * Returns index of the task to be deleted.
     *
     * @return Index of the task to be deleted.
     */
    public int getIndex() {
        return index;
    }

    public String getDescription() {
        return "";
    }

    public String getDate() {
        return "";
    }

    public String getKeyword() {
        return "error";
    }

    public void execute(TaskList tasks, Ui ui) {
        Task removedTask = tasks.removeTask(getIndex());
        ui.reportRemove(removedTask, tasks.getNumOfTasks());
    }
}