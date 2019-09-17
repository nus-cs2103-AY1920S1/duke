package duke.command;

import duke.Ui;
import duke.data.DukeData;

import java.io.IOException;

/**
 * The FindCommand helps the user search for tasks in the list which matches
 * the given keyword by the user.
 */
public class FindCommand implements Command {
    private String findKeyword;

    /**
     * Creates a FindCommand with the given keyword.
     * @param keyword the word the user wants Duke to search for in the Duke's tasks list
     */
    public FindCommand(String keyword) {
        this.findKeyword = keyword;
    }

    /**
     * Execute method which calls the method upon initialisation of the object.
     * @param dukeData the storage object of the program
     * @param ui       ui object which handles output of user interaction
     * @return a string representation of Duke's findings in its tasklist
     */
    @Override
    public String execute(DukeData dukeData, Ui ui) throws IOException {
        return ui.showFind(this.findKeyword, dukeData.load());
    }
}
