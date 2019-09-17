package duke.command;

import duke.Ui;
import duke.data.DukeData;

import java.io.IOException;

/**
 * The ByeCommand handles the Duke's farewell to the user.
 */
public class ByeCommand implements Command {

    /**
     * Execute method which calls the method upon initialisation of the object.
     * @param dukeData the storage object of the program
     * @param ui ui object which handles output of user interaction
     * @return a String representation of the Duke's farewell
     */
    public String execute(DukeData dukeData, Ui ui) throws IOException {
        dukeData.exit();
        return ui.showFarewell();
    }
}
