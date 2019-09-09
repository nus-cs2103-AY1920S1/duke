package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import java.io.IOException;

/**
 * Represents a command to sort the tasks;
 */
public class SortCommand extends Command {

    private String category;
    /**
     * Creates an SortCommand instance with an input task.
     * @param category string representing what the sorting should be based on.
     */
    public SortCommand(String category) throws DukeException {
        if(category.equals("time") || category.equals("description") || category.equals("type")) {
            this.category = category;
        } else {
            throw new DukeException("\u2639 The category for sorting can only be time, description, status or type.");
        }
    }

    /**
     * Prints out the response showing a sorted list of tasks.
     *
     * @param storage the storage storing task data.
     * @param tasks the list of tasks.
     * @return String representing the response.
     */
    public String execute(Storage storage, TaskList tasks) throws DukeException {
        try {
            StringBuilder listOfTasks = new StringBuilder("Here are the tasks in your list sorted by " + category + ":\n     ");
            tasks.sortTasks(category);
            for (int i = 0; i < tasks.getSize(); i++) {
                listOfTasks.append(i + 1).append(".").append(tasks.getElement(i));
                if (i != tasks.getSize() - 1) listOfTasks.append("\n     ");
            }
            storage.writeToFile(tasks.generateInfo());
            return listOfTasks.toString();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

}
