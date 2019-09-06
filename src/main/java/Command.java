/**
 * Abstract class to represent the possible commands given to Duke.
 */

public abstract class Command {

    /**
     * Executes the different commands respectively.
     * @param tasks Arraylist of tasks.
     * @param ui User interaction that comes with the command.
     * @param storage To deal with saving tasks into the file after executing the command.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

}
