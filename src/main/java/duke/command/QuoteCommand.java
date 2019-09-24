package duke.command;

import duke.Ui;
import duke.data.DukeData;
import duke.data.TaskList;

/**
 * The QuoteCommand handles and generates quotes for the user.
 */
public class QuoteCommand implements Command {

    /**
     * Execute method which calls the method upon initialisation of the object.
     * @param dukeData the storage object of the program
     * @param ui       ui object which handles output of user interaction
     * @param taskList the list of tasks that is stored in the Duke program
     * @return a string representation of the output for the quote command
     */
    @Override
    public String execute(DukeData dukeData, Ui ui, TaskList taskList) {
        return ui.showQuote();
    }
}
