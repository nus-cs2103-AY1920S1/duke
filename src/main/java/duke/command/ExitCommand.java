package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UserInterface;

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
     * @param commandLineUserInterface the user interface
     * @param storage the storage for the tasks
     */
    public String execute(TaskList tasks, UserInterface commandLineUserInterface, Storage storage) {
        try {
            storage.save(tasks.save());
        } catch (IOException io) {
            return commandLineUserInterface.showError("I/O Error occurred");
        } finally {
            return commandLineUserInterface.showExitMessage();
        }
    }

}
