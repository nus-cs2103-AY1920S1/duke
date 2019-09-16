package duke.command;

import duke.Ui;
import duke.data.DukeData;

import java.io.IOException;

public class DataCommand implements Command {
    /**
     * Execute method which calls the method upon initialisation of the object.
     * @param dukeData the storage object of the program
     * @param ui       ui object which handles output of user interaction
     * @return a string representation of the data file in the Duke program
     */
    @Override
    public String execute(DukeData dukeData, Ui ui) throws IOException {
        return ui.showData(dukeData);
    }
}
