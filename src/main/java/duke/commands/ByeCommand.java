package duke.commands;

import duke.exceptions.DukeException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.UiResponse;

/** Command invoked when "bye" command is encountered. */
public class ByeCommand extends Command {
    /**
     * Upon execution, will change the <code>isExit</code> boolean is true. Will also
     * save the TaskList a final time before Duke exits.
     *
     * @param ui       UiResponse object that is responsible for returning a String response to GUI
     * @param storage  Storage object respnsible for saving the Tasks into a pre-defined format
     * @param allTasks TaskList object containing all tasks.
     * @return String representing Duke's response
     * @throws DukeException re-thrown from underlying method calls.
     */
    public String execute(UiResponse ui, Storage storage, TaskList allTasks) throws DukeException {
        //Will cause while loop in Duke to exit
        this.isExit = true;

        //Parent method invoked to save TaskList
        return super.execute(ui, storage, allTasks);
    }
}
