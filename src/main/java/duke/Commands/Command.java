package duke.Commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.Exceptions.DukeException;

public abstract class Command {

    /**
     * Abstract method execute method as part of the abstract class Command.
     * @param currentTaskList taskList containing all the current Task
     * @param ui class that handles all I/O managements
     * @param storage class that handles all file storage and loading
     * @return a String output for the specific Commands
     */
    public abstract String execute(TaskList currentTaskList, Ui ui, Storage storage) throws DukeException;

}
