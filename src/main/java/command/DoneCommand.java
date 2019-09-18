package command;
import task.Task;
import task.TaskList;

/**
 *<h1> DoneCommand</h1>
 * The DoneCommand class
 * 1) Instructs the relevant TaskList to mark the indexed task as Done
 * 2) Instructs the Textformatter to return a message for the user
 *
 */

public class DoneCommand extends Command {
    int doneIndex;
    Task done;

    /**
     * Constructor for DoneCommand Object
     * Stores new task as <param>done</param>
     *
     * @param number is the index for the task that is donw
     */
    public DoneCommand(int number) {
        doneIndex = number;
    }

    /**
     * Marks indexed task as done in the tasklist and return the formatted done string
     *
     * @param reference is the tasklist being used by the program
     * @return String the formatted output, after running through formatOutput()
     */
    @Override
    public String executeCommand(TaskList reference) {
        this.reference = reference;
        done = reference.taskDone(doneIndex);
        return this.formatOutput();
    }

    /**
     * Returns the formatted command as a formatted string
     *
     *@return String formatted
     */

    public String formatOutput() {

        return TextFormatter.doneFormat(done);
    }


}


