package seedu.duke;

import java.io.IOException;

/**
 * Represents a Delete Command.
 * A <code>DeleteCommand</code> object corresponds to a command with a description that starts with "delete" and
 * contains the index of the task to be deleted.
 */
public class DeleteCommand extends Command {

    private int index;

    /**
     * Constructor of the DeleteCommand class.
     *
     * @param index the index of the task to be deleted
     */
    DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Prints the remove message, the task deleted and the number of tasks left in the list.
     * Delete the task in the arraylist and updates the datafile.
     *
     * @param list the TaskList object that is handling the arraylist of the datafile
     * @param ui the UserInterface object that handles the interaction with users
     * @param storage the Storage object that stores and handles the path to datafile
     * @return the output string
     * @throws IOException upon wrong input
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws IOException {
    assert list != null : "Cannot delete from an empty list";
    assert list.getSize() >= index : "Index is invalid";
        String output = Ui.printRemoveMsg() + list.getTask(index) + "\n";
        list.deleteTask(index);
        storage.writeToFile(list);
        output += Ui.printNumTask(list);
        return output;
    }
}
