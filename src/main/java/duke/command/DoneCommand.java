package duke.command;

import duke.todo.Task;
import duke.todo.TaskList;
import duke.ui.Ui;

/**
 * DoneCommand class for Done command.
 */
public class DoneCommand implements Command {
    private int index;

    /**
     * Constructs a done command based on the index input.
     *
     * @param index Index of the task done.
     */
    public DoneCommand(int index) {
        this.index = index - 1;
    }

    /**
     * Generates task type.
     *
     * @return Done.
     */
    public String getTaskType() {
        return "done";
    }

    /**
     * Returns the index of the task done.
     *
     * @return Index of the task done.
     */
    public int getIndex() {
        return index;
    }

    public String getDescription() {
        return "error";
    }

    public String getDate() {
        return "error";
    }

    public String getKeyword() {
        return "error";
    }

    public void execute(TaskList tasks, Ui ui) {
        Task taskMarkedDone = tasks.markTaskDone(getIndex());
        ui.reportDone(taskMarkedDone);
    }
}