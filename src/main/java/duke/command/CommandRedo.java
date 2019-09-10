package duke.command;

import duke.task.TaskList;
import duke.util.DukeException;
import duke.util.History;
import duke.util.Storage;
import duke.util.Ui;

/**
 * A Command to redo a later command in the session's history.
 */
public class CommandRedo extends Command {

    /**
     * Redoes the next latest command in the session's history, saves the task list, and returns a confirmation.
     * @param tasks The TaskList containing the user's added Tasks.
     * @param ui The UI to interact with the user by printing instructions/messages.
     * @param storage Storage to use for loading/saving tasks from/to a file on the hard disk.
     * @param history History of commands for the current session.
     * @return Duke's response to the Command as a String.
     * @throws DukeException If redoing the next latest command fails.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage, History history) throws DukeException {
        String command = history.redo(tasks);
        storage.save(tasks);
        return ui.getRedoMessage(command);
    }
}
