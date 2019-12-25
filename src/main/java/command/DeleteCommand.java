package command;

import exception.DeleteParameterException;
import exception.DukeException;
import exception.UpdateStateException;
import run.Storage;
import run.TaskList;
import run.Ui;

import java.io.IOException;

/**
 * Extends Command class and is used to delete an existing task from TaskList.
 */
public class DeleteCommand extends Command {
    protected String rawString;
    protected int taskNum;

    /**
     * Constructor for delete command.
     * @param rawString complete unparsed user input of delete creation request
     * @throws DeleteParameterException if user does not provide a valid int number
     */
    public DeleteCommand(String rawString) throws DeleteParameterException {
        this.rawString = rawString;
        String[] splited = rawString.split(" ");
        if (splited[1].matches("^[0-9]*[1-9][0-9]*$") && splited.length == 2) {
            taskNum = Integer.parseInt(splited[1]);
        } else {
            throw new DeleteParameterException(DukeException.DELETE_PARAMETER_EXCEPTION_MESSAGE);
        }
    }

    /**
     * Deletes task from current TaskList based on user task number input, updates the
     * state of the storage by deleting task as well and interacts/updates the user through the ui.
     * @param tasks current TaskList with all current tasks
     * @param ui current user interface
     * @param storage current storage state
     * @return String output of executed command to be shown to the user
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, UpdateStateException {
        String output = tasks.delete(taskNum);
        storage.updateState(tasks);
        return output;
    }

}