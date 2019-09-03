package duke.command;

import duke.task.TaskList;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents the <code>Command</code> to exit from the <code>Duke</code> application.
 *
 * @author Clarence Koh
 * @version 1.0
 * @since 29th August 2019
 */
public class ExitCommand extends Command {

    /**
     * Class constructor.
     */
    public ExitCommand() {
        super();
    }

    /**
     * This method when called results in the closing of all processes in the <code>Duke</code>
     * and exits the program.
     *
     * @param tasks The task lists which contains all the user added tasks.
     * @param ui The user interface which deals with user input and interaction.
     * @param storage The storage to load and save task data into the output file.
     * @throws DukeException If there is a problem with data processing, loading or saving.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printBye();
        super.isExit = true;
    }
}