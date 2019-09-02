package command;

import java.io.IOException;

import exception.UpdateStateException;
import run.TaskList;
import run.Ui;
import run.Storage;

public abstract class AddCommand extends Command {
    TaskList tasks;
    Ui ui;
    Storage storage;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }

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