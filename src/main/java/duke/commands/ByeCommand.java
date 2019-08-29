package duke.commands;

import duke.exceptions.DukeException;
import duke.utils.Ui;
import duke.utils.Storage;
import duke.utils.TaskList;

/** Command invoked when "bye" command is encountered */
public class ByeCommand extends Command {
    /**
     * Upon execution, will change the <code>isExit</code> boolean is true. Will also
     * save the TaskList a final time before Duke exits.
     * @param ui Ui object that is responsible for printing output as a response
     * @param storage Storage object respnsible for saving the Tasks into a pre-defined format
     * @param allTasks TaskList object containing all tasks.
     * @throws DukeException
     */
    public void execute(Ui ui, Storage storage, TaskList allTasks) throws DukeException {
        //Will cause while loop in Duke to exit
        this.isExit = true;

        //Parent method invoked to save TaskList
        super.execute(ui, storage, allTasks);
    }
}
