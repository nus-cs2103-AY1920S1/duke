package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * The <code>ExitCommand</code> is created when the user enters <code>"bye"</code>. The exit command will show the exit
 * message and save all tasks in the list of tasks in the {@link TaskList} class object to the
 * {@link duke.storage.Storage}. The storage is the file path specified by {@link duke.main.Duke} and
 * {@link duke.storage.Storage}.
 */
public class ExitCommand implements Command {

    /**
     * Executes the command. This will save the list of tasks in {@link TaskList} into the storage. The storage is the
     * file path specified by {@link duke.main.Duke} and {@link Storage}.
     * @param tasks the list of tasks
     * @param ui the user interface
     * @param storage the storage for the tasks
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.save(tasks.save());
        } catch (IOException io) {
            ui.showError("I/O Error occurred");
        } finally {
            ui.showExitMessage();
        }
    }

    /**
     * Returns <code>true</code> if the command is an exit command and <code>false</code> otherwise.
     * @return <code>true</code>
     */
    public boolean isExit() {
        return true;
    }

}
