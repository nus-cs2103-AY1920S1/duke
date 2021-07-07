package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * Represents an add command that Duke will operate.
 */
public class AddCommand implements Command {
    Task task; //The task should add.

    /**
     * The initiate constructor.
     *
     * @param task Task should add.
     */
    public AddCommand(Task task) {
        this.task = task;
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
        tasks.addTask(task);
        ArrayList<Task> list = tasks.getList();
        storage.saveFile(list);
        return ui.showAdd(task, list.size());
    }
}
