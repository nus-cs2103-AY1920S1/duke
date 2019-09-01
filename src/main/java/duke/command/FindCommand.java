package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a Find Command.
 */
public class FindCommand extends Command {
    private String tofind;

    public FindCommand(String s) {
        this.tofind = s;
    }

    /**
     * Method that returns true only if this is an instance of an ExitCommand.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the Command: Finds the Task(s) in tasks that match the specified string.
     *
     * @param tasks   current TaskList instance
     * @param ui      current UI instance
     * @param storage current Storage instance
     * @throws DukeException DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        TaskList containsList = tasks.find(this.tofind);
        ui.printLine("Here are the matching tasks in your list:");
        ui.printLine(containsList.printAllTasks());
    }
}
