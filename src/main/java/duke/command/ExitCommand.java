package duke.command;

import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a "bye" command.
 */
public class ExitCommand extends Command {
    /**
     * Saves all current Tasks into the file.
     *
     * @param tasks List of Tasks Duke is currently tracking.
     * @param ui User Interface of Duke that handles input and output to and from the command line.
     * @param storage Storage where the Tasks are retrieved from and stored to.
     * @throws DukeException If the tasks cannot be written and saved into file properly.
     */
    @Override
    public void executeCli(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.saveTasks(tasks);
    }

    @Override
    public String executeGui(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.saveTasks(tasks);
        return ui.getExitMsg();
    }

    /**
     * Returns true as it is an Exit command.
     *
     * @return <code>true</code>
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
