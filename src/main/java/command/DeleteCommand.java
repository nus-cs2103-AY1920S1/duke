package command;

import error.DukeException;
import java.io.IOException;
import task.Task;
import utils.Parser;
import utils.Storage;
import utils.TaskList;
import utils.Ui;

/**
 * Deals with operation to delete tasks: delete commands.
 */
public class DeleteCommand extends Command {
    protected String command;
    protected int pos;

    public DeleteCommand(String command) {
        this.command = command;
    }

    /**
     * Executes operation to update existing task.
     *
     * @param tasks TaskList to perform changes from
     * @param ui Ui to generate message outputs
     * @param storage Object to save tasks
     * @return String generate message as output from successful operation
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            if (Parser.isValidNumber(command, tasks.getList().size())) {
                pos = Integer.parseInt(command);
                Task currTask = tasks.deleteTask(pos);
                storage.updateTasks(currTask, "delete", pos - 1);
                return ui.printDeletedTask(currTask, tasks.getList().size());
            }
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
        return "";
    }

    public boolean isExit() {
        return false;
    }
}