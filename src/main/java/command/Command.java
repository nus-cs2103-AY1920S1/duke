package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Abstract command which has many different type of commands.
 *
 */
public abstract class Command {

    /**
     * Executes the given line instruction passed into the object.
     *
     * @param tasklist Tasklist object containing the data structure.
     * @param ui duke Ui object responsible for interface response.
     * @param storage duke Storage object to make changes to hard drive data.
     * @return Response string from the Bot to the user.
     */
    public abstract String getResponse(TaskList tasklist, Ui ui, Storage storage) throws DukeException;

    /**
     * Indicates if the Command signals for program termination.
     *
     * @return A boolean if the program should terminate.
     */
    public boolean isExit() {
        return false;
    }

}
