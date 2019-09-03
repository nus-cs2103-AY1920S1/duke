package duke.command;

import duke.task.Task;

import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a "delete" command.
 */
public class DeleteCommand extends Command {
    /**
     * Constructor for task deleting command.
     *
     * @param taskId ID of Task to be deleted.
     */
    public DeleteCommand(String taskId) {
        super(taskId);
    }

    /**
     * Removes Task associated with the taskId from the current list of Tasks.
     * Ui then prints the Task that was deleted.
     *
     * @param tasks List of Tasks Duke is currently tracking.
     * @param ui User Interface of Duke that handles input and output to and from the command line.
     * @param storage Storage where the Tasks are retrieved from and stored to.
     * @throws DukeException If taskId does not represent a valid number.
     */
    @Override
    public void executeCli(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.remove(args);
        ui.printDeleteTaskMsg(task);
    }

    @Override
    public String executeGui(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.remove(args);
        return ui.getDeleteTaskMsg(task);
    }
}
