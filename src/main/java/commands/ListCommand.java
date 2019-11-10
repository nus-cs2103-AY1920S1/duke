package commands;

import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

/**
 * Class for the command that prompts the program to terminate.
 */
public class ListCommand implements Command {

    /**
     * Constructs the list command.
     */
    public ListCommand() {
    }

    /**
     * Indicates whether this is an exit command.
     *
     * @return false
     */
    public boolean isExitCommand() {
        return false;
    }

    /**
     * Executes the list command.
     * All tasks in the tasklist will be displayed to the user.
     * Tasks are represented by their string representation.
     *
     * @param tasks tasklist that stores all the tasks
     * @param ui ui object that deals with user interaction
     * @param storage storage object that deals with writing into the text file
     * @return string representation of the command response
     * @see  Task
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder stringOfItems = new StringBuilder("     Here are the tasks in your list:\n");
        int numOfItems = tasks.getNumOfTask();
        for (int i = 0; i < numOfItems; i++) {
            stringOfItems.append("     " + (i + 1) + ". " + tasks.getTaskByIndex(i)
                    + (i == numOfItems - 1 ? "" : "\n"));
        }
        return ui.showResultOfCommand(stringOfItems.toString());
    }
}
