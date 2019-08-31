package seedu.duke;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a Find Command.
 * A <code>FindCommand</code> object corresponds to a command with a
 * description that starts with "find" and a keyword.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Constructor of FindCommand.
     *
     * @param keyword the keyword to find
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Prints the matching message and prints out a list of all the tasks containing the keywords.
     *
     * @param list the TaskList object that is handling the arraylist of the datafile
     * @param ui the UserInterface object that handles the interaction with users
     * @param storage the Storage object that stores and handles the path to datafile
     * @throws IOException upon wrong input
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws IOException {
        ui.printMatchingMsg();
        ArrayList<Task> matchingList = list.findMatching(keyword);
        ui.printMatchingList(matchingList);
    }

}
