package command;

import task.Task;
import task.TaskList;

/** The AddCommand class.
 * 1) Instructs the relevant TaskList to add a new task
 * 2) Instructs the Textformatter to return a message for the user
 */

public class AddCommand extends Command {
    private Task added;

    /**
     * Constructor for AddCommand Object.
     * Stores new task as added
     */

    public AddCommand(Task x) {
        added = x;
    }

    /**
     * Adds new task to tasklist using addTasks and formats add String.
     *
     * @param reference is the tasklist being used by the program
     * @return String the formatted output, after running through formatOutput()
     */

    @Override
    public String executeCommand(TaskList reference) {
        this.reference = reference;
        added = reference.addTasks(added);
        return this.formatOutput();
    }

    /**
     * Returns the formatted command as a formatted string.
     *
     *@return String formatted
     */

    public String formatOutput() {
        return TextFormatter.addFormat(added,reference.getSize());
    }

}



