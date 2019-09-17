package duke.command;

import duke.Ui;
import duke.data.DukeData;

import java.io.IOException;

/**
 * The ListCommand handles the command where the user requests to view the tasks in his/her list.
 */
public class ListCommand implements Command {

    /**
     * Execute method which calls the method upon initialisation of the object.
     * @param dukeData the storage object of the program
     * @param ui       ui object which handles output of user interaction
     * @return a string representation of the list of tasks added to the Duke Program
     */
    @Override
    public String execute(DukeData dukeData, Ui ui) throws IOException {
        return ui.showList(dukeData.load().getList());
    }
}
