public abstract class Command {

    /**
     * Abstract class blueprint for all commands
     * @param tasks to handle the tasks
     * @param ui to get user input
     * @param storage to store tasks
     * @throws DukeException
     */
    public abstract void execute (TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * If bye is keyed in,
     * @return true to terminate the input
     */
    public abstract boolean isExit();

}
