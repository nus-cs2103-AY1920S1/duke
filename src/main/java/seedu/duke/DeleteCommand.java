package seedu.duke;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a Delete Command.
 * A <code>DeleteCommand</code> object corresponds to a command with a description that starts with "delete" and
 * contains the index of the task to be deleted.
 */
public class DeleteCommand extends Command {

    private ArrayList<Object> indexList;

    /**
     * Constructor of the DeleteCommand class.
     *
     * @param indexList the list of index of the tasks to be deleted
     */
    DeleteCommand(ArrayList<Object> indexList) {
        this.indexList = indexList;
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
        String output = Ui.printRemoveMsg();
        for (Task t : convertToTask(list)) {   // list here is the original list to delete from
            output += t + "\n";
            list.deleteTask(t);
            storage.writeToFile(list);
        }
        output += Ui.printNumTask(list);
        return output;
    }

    private ArrayList<Task> convertToTask(TaskList list) {
        ArrayList<Task> tasksToDelete = new ArrayList<>();
        for (Object index : indexList) {
            tasksToDelete.add(list.getTask((int) index));
        }
        return tasksToDelete;
    }
}
