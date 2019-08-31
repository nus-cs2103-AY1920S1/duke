package seedu.duke;

/**
 * Represents a List Command.
 * A <code>ListCommand</code> object corresponds to a command with a description "list".
 */
public class ListCommand extends Command {

    /**
     * Constructor of the ListCommand class.
     */
    public ListCommand() {
    }

    /**
     * Prints all the tasks in the list.
     *
     * @param list the TaskList object that is handling the arraylist of the datafile
     * @param ui the UserInterface object that handles the interaction with users
     * @param storage the Storage object that stores and handles the path to datafile
     */
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.printList(list);
    }

}
