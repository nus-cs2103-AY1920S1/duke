package seedu.duke;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a Done Command.
 * A <code>DoneCommand</code> object corresponds to a command with a description that starts with "done" and
 * contains the index of the task to be marked as done.
 */
public class DoneCommand extends Command {

    private ArrayList<Object> indexList;

    /**
     * Constructor of the DoneCommand class.
     *
     * @param indexList the list of index of the tasks to be marked as done
     */
    public DoneCommand(ArrayList<Object> indexList) {
        this.indexList = indexList;
    }

    /**
     * Marked the task as done and updates the status icon of the task.
     * Updates the status of the task in the datafile.
     *
     * @param list the TaskList object that is handling the arraylist of the datafile
     * @param ui the UserInterface object that handles the interaction with users
     * @param storage the Storage object that stores and handles the path to datafile
     * @return the output string
     * @throws IOException on input error
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws IOException {
        assert list != null : "Cannot mark done from an empty list";
        String output = "";
        output += ui.printDoneMsg();

        for (Object index : indexList) {
            list.getTask((int) index).markAsDone();
            list.getTask((int) index).setDoneDateTime();
            output += list.getTask((int) index) + "\n";
            storage.writeToFile(list);
        }
        return output;
    }

}
