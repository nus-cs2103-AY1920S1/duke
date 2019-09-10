package duke.command;

import duke.tasklist.TaskList;
import duke.storage.Storage;
import duke.ui.UI;

/**
 * Abstract class to represent all the commands available in this program.
 */
public abstract class Command {

    /**
     * String that contains the main message of the command.
     */
    protected String input;

    /**
     * Constructor that takes in the main message of the command. Can be empty.
     * @param message The main message of the command.
     */
    public Command(String message) {
        this.input = message;
    }

    /**
     * Used to obtain the main message of the command.
     * @return String of the main message of the command.
     */
    public String getMessage() {
        return input;
    }

    /**
     * Used to execute the command and modify the list of tasks accordingly.
     * @param listOfTasks List of tasks to be modified according to the input.
     * @param storage Used to modify the files in the hard drive.
     * @param ui Prints out all the messages.
     * @throws Exception For when there are any errors when executing the method.
     */
    public abstract void execute(TaskList listOfTasks, Storage storage, UI ui) throws Exception;
}
