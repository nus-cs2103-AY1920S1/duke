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
    private boolean isExit;


    /**
     * Executes the given line instruction passed into the object.
     *
     * @param tasklist Tasklist object containing the data structure.
     * @param ui duke Ui object responsible for interface response.
     * @param storage duke Storage object to make changes to hard drive data.
     */
    public abstract void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return this.isExit;
    }

    public void setExit(boolean exit) {
        isExit = exit;
    }
}
