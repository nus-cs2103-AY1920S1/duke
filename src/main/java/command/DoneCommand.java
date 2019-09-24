package command;

import exception.DoneParameterException;
import exception.UpdateStateException;
import run.Storage;
import run.TaskList;
import run.Ui;

import java.io.IOException;

/**
 * Extends Command class and used to mark an exisiting task in TaskList as done.
 */
public class DoneCommand extends Command {
    protected String rawString;
    protected int taskNum;

    /**
     * Constructor for done command.
     * @param rawString complete unparsed user input of done creation request
     * @throws DoneParameterException if user does not provide a valid int number
     */
    public DoneCommand(String rawString) throws DoneParameterException {
        this.rawString = rawString;
        String[] splited = rawString.split(" ");
        if (splited[1].matches("^[0-9]*[1-9][0-9]*$") && splited.length == 2) {
            taskNum = Integer.parseInt(splited[1]);
        } else {
            throw new DoneParameterException("Invalid parameter! Try the format: done (task number)");
        }
    }

    /**
     * Tries to mark relevant task in TaskList as done, updates state in storage and interacts/updates
     * the user through the ui. Catches IOException when accessing the storage state file and
     * UpdateStateException if exception faces while updating storage state file.
     * @param tasks current TaskList with all current tasks
     * @param ui current user interface
     * @param storage current storage state
     * @return String output of executed command to be shown to the user
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String output = tasks.done(taskNum);
        try {
            storage.updateState(tasks);
        } catch (IOException ex) {
            Ui.showError("IO exception caught while marking task as done!");
        } catch (UpdateStateException ex) {
            Ui.showError(ex.getMessage());
        }
        return output;
    }

}