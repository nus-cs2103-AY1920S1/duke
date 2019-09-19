package seedu.duke;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a Find Command.
 * A <code>FindCommand</code> object corresponds to a command with a
 * description that starts with "find" and a keyword.
 */
public class FindCommand extends Command {

    private ArrayList<Object> findList;

    /**
     * Constructor of FindCommand.
     *
     * @param findList the list of keywords to find
     */
    public FindCommand(ArrayList<Object> findList) {
        this.findList = findList;
    }

    /**
     * Prints the matching message and prints out a list of all the tasks containing the keywords.
     *
     * @param list the TaskList object that is handling the arraylist of the datafile
     * @param ui the UserInterface object that handles the interaction with users
     * @param storage the Storage object that stores and handles the path to datafile
     * @return the output string
     * @throws IOException upon wrong input
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws IOException {
        String output = ui.printMatchingMsg();
        ArrayList<Task> matchingList = new ArrayList<>();
        for (Object keyword: findList) {
            matchingList.addAll(list.findMatching((String) keyword));
        }
        output += ui.printMatchingList(matchingList);
        return output;
    }

}
