package command;

import exception.DukeException;
import filewriter.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Command to end program.
 */
public class ExitCommand extends Command {
    public ExitCommand() {
        super.type = FullCommand.BYE;
    }


    /** Used by Duke class method run to check for exit command and terminate program if exit command is found.
     * @return true as command is ExitCommand.
     */
    public boolean isExit() {
        assert(super.type.getName().equals("bye"));
        return true;
    }

    /**
     * Closes FileWriter used by storage.
     * @param tasks current TaskList object used in this instance of Duke. Not needed here.
     * @param ui Instance of user interface to print feedback to user. Not needed here.
     * @param storage closes FileWriter of storage. Changes to Task List are recorded in storage.filepath.
     * @throws DukeException if invalid filepath. (Directory tampered with when program is running)
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.write(tasks);
    }
}
