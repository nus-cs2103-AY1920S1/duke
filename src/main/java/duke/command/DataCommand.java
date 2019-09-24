package duke.command;

import duke.Ui;
import duke.data.DukeData;
import duke.data.TaskList;

import java.io.IOException;

/**
 * The DataCommand handles the command where the user requests to view the data stored
 * in the local disc.
 */
public class DataCommand implements Command {

    /**
     * Execute method which calls the method upon initialisation of the object.
     * @param dukeData the storage object of the program
     * @param ui       ui object which handles output of user interaction
     * @param taskList the list of tasks that is stored in the Duke program
     * @return a string representation of the data file in the Duke program
     */
    @Override
    public String execute(DukeData dukeData, Ui ui, TaskList taskList) throws IOException {
        return ui.showData(dukeData);
    }
}
