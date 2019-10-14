package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represent a command to show errors in Duke.
 */
public class ErrorCommand implements Command {
    private DukeException exception;

    /**
     * Creates a command with the error to be handled.
     * 
     * @param e a DukeException that is to be handled
     */
    public ErrorCommand(DukeException e) {
        this.exception = e;
    }

    /**
     * Executes the command and display the error message.
     * 
     * @param tasks   a tasklist containing all the existing tasks
     * @param ui      a ui which will output the message
     * @param storage a storage object which will write to the file
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return ui.errorMessage(exception);
    }
}