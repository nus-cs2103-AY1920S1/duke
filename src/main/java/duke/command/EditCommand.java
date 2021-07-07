package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.Date;

/**
 * Represents a done command that Duke will operate.
 */
public class EditCommand implements Command {
    int order; //The order of task that is edited.
    Date newTime; //The update time.

    /**
     * Initiates object.
     *
     * @param order The index should be edit.
     * @param newTime The updated time.
     */
    public EditCommand(int order, Date newTime) {
        this.order = order;
        this.newTime = newTime;
    }

    /**
     * Returns should the command exit.
     *
     * @return whether should exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command.
     *
     * @param tasks  Tasks store in tasklist.
     * @param ui User interaction.
     * @param storage The storage area.
     * @throws DukeException  If there is mistake in operation.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> taskList = tasks.getList();
        Task newTask = tasks.editTask(order, newTime);
        storage.saveFile(taskList);
        return ui.showEdit(newTask, taskList.size(), order);
    }
}
