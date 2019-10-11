package command;

import task.TaskList;

/*** The abstract Command class.
 * 1) Superclass for all other type of commands
 */

public abstract class Command {
    TaskList reference;

    /**
     *Instructs the relevant entities to do their assigned tasks.
     *
     * @param reference is the tasklist being used by the program
     * @return String the formatted output, after running through formatOutput()
     */
    public String executeCommand(TaskList reference) {
        this.reference = reference;
        return this.formatOutput();
    }

    /**
     * Abstract method to return the formatted String after passing through TextFormatter.
     *
     *@return String formatted
     */
    public abstract String formatOutput();

}
