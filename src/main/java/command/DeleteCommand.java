package command;

import task.Task;
import task.TaskList;

/**The DeleteCommand class.
 * 1) Instructs the relevant TaskList to delete the indexed task
 * 2) Instructs the Textformatter to return a message for the user
 *
 */
public class DeleteCommand extends Command {
    int deletedIndex;
    Task removed;

    /**
     * Constructor for DeleteCommand Object and stores new task.
     *
     * @param number String format of new task
     */
    public DeleteCommand(int number) {
        deletedIndex = number;
    }

    /**
     * Deletes the indexed task from tasklist using deleteTask and formats delete String.
     *
     * @param reference is the tasklist being used by the program
     * @return String the formatted output, after running through formatOutput()
     */
    @Override
    public String executeCommand(TaskList reference) {
        this.reference = reference;
        removed = reference.deleteTask(deletedIndex);
        return this.formatOutput();
    }

    /**
     * Returns the formatted command as a formatted string.
     *
     *@return String formatted
     */

    public String formatOutput() {
        return TextFormatter.deleteFormat(removed,reference.getSize());
    }


}
