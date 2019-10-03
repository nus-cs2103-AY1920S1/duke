package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;

/**
 * Tells user that the program is terminating.
 */
public class ExitCommand extends Command {
    /**
     * Instructs the program that it is terminating.
     *
     * @return true to indicate program is terminating
     */
    @Override
    public boolean canEnd() {
        return true;
    }

    /**
     * Prints exit message.
     * Saves task list to hard disk.
     *
     * @param tasks contains task list
     * @param ui deals with interaction with users
     * @param storage deals with loading and saving of task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExitCommand();
        try {
            storage.save(tasks);
        } catch (IOException e) {
            ui.showLoadingError(e.getMessage());
        }
    }
}
