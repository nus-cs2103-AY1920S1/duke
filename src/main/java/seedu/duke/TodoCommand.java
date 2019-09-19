package seedu.duke;

import java.io.IOException;

/**
 * Represents the Todo Command.
 * A <code>TodoCommand</code> object corresponds to a command with a description that starts with "todo".
 */
public class TodoCommand extends Command {

    String command;

    /**
     * Constructor of the Todo command.
     *
     * @param command the description of the command
     */
    public TodoCommand(String command) {
        this.command = command;
    }

    /**
     * Adds the task to the list of tasks.
     * Prints the adding message, the task added and the number of tasks in the list.
     * Update the data file with the new task.
     *
     * @param list the TaskList object that is handling the arraylist of the data file
     * @param ui the UserInterface object that handles the interaction with users
     * @param storage the Storage object that stores and handles the path to data file
     * @return the output string
     * @throws IOException on the incorrect input
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws IOException {
        list.addTask(new Todo(command));
        String output = ui.printAddMsg() + ui.printLatest(list) + ui.printNumTask(list);
        storage.appendToFile(list);
        return output;
    }

}
