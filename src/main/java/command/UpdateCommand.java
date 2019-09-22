package command;

import error.DukeException;
import task.Task;
import utils.Parser;
import utils.Ui;
import utils.Storage;
import utils.TaskList;

/**
 * Deals with operation to update tasks: done commands.
 */
public class UpdateCommand extends Command {
    protected String command;
    protected int pos;

    public UpdateCommand(String command) {
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
                Task currTask = tasks.updateTask(pos);
                storage.updateTasks(currTask, "done", pos - 1);
                return ui.printDoneTask(currTask);
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