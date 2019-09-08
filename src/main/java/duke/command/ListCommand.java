package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents a command to list out all the tasks;
 */
public class ListCommand extends Command {

    /**
     * Prints out the response showing a list of tasks.
     *
     * @param storage the storage storing task data.
     * @param tasks the list of tasks.
     * @return String representing the response.
     */
    public String execute(Storage storage, TaskList tasks) {
        StringBuilder listOfTasks = new StringBuilder("Here are the tasks in your list:\n     ");
        for (int i = 0; i < tasks.getSize(); i++) {
            listOfTasks.append(i + 1).append(".").append(tasks.getElement(i));
            if (i != tasks.getSize() - 1) listOfTasks.append("\n     ");
        }
        return listOfTasks.toString();
    }

}
