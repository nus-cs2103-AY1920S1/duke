package duke.command;

import duke.Ui;
import duke.data.DukeData;

/**
 * The QuoteCommand handles and generates quotes for the user.
 */
public class QuoteCommand implements Command {

    /**
     * Execute method which calls the method upon initialisation of the object.
     * @param dukeData the storage object of the program
     * @param ui       ui object which handles output of user interaction
     * @return a string representation of the output for the quote command
     */
    @Override
    public String execute(DukeData dukeData, Ui ui) {
        return ui.showQuote();
    }
}
