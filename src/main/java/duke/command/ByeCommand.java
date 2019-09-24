package duke.command;

import duke.Ui;
import duke.data.DukeData;
import duke.data.TaskList;

import java.io.IOException;

/**
 * The ByeCommand handles the Duke's farewell to the user.
 */
public class ByeCommand implements Command {

    /**
     * Execute method which calls the method upon initialisation of the object.
     * @param dukeData the storage object of the program
     * @param ui ui object which handles output of user interaction
     * @param taskList the list of tasks that is stored in the Duke program
     * @return a String representation of the Duke's farewell
     */
    public String execute(DukeData dukeData, Ui ui, TaskList taskList) throws IOException {
        dukeData.exit();
        return ui.showFarewell();
    }
}
