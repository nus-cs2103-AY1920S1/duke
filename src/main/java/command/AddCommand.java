package command;

import java.io.IOException;

import exception.UpdateStateException;
import run.TaskList;
import run.Ui;
import run.Storage;

/**
 * Abstract class for a command that adds tasks into tasks list
 */
public abstract class AddCommand extends Command {
    TaskList tasks;
    Ui ui;
    Storage storage;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Checks if this command is an exit ("bye") command
     * @return false boolean since command is not exit ("bye") command
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Updates state of save file to latest tasks from TaskList.
     * Catches IO exception and UpdateStateException that may be thrown while
     * updating state.
     */
    public void addCommandUpdateState() {
        try {
            storage.updateState(tasks);
        } catch (IOException ex) {
            Ui.showError("IO exception caught while adding task!");
        } catch(UpdateStateException ex) {
            Ui.showError(ex.getMessage());
        }
    }
}