package command;

import java.io.IOException;

import exception.UpdateStateException;
import run.TaskList;
import run.Ui;
import run.Storage;

/**
 * Abstract class for a command that adds tasks into tasks list that extends Command class.
 */
public abstract class AddCommand extends Command {
    protected TaskList tasks;
    protected Ui ui;
    protected Storage storage;

    /**
     * Abstract method that adds information to current TaskList, updates the
     * state of the TaskList through storage and interacts/updates the user through the ui.
     * @param tasks current TaskList with all current tasks
     * @param ui current user interface
     * @param storage current storage state
     * @return String output of executed command to be shown to the user
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, UpdateStateException;

    /**
     * Updates state of save file to latest tasks from TaskList.
     * Catches IO exception and UpdateStateException that may be thrown while
     * updating state.
     */
    public void addCommandUpdateState() throws IOException, UpdateStateException {
        try {
            storage.updateState(tasks);
        } catch (IOException ex) {
            throw new IOException("IO exception caught while adding task!");
        } catch (UpdateStateException ex) {
            throw new UpdateStateException(ex.getMessage());
        }
    }
}