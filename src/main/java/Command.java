public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks   The TaskList containing all existing tasks.
     * @param ui      The Ui for printing purposes.
     * @param storage The Storage for saving tasks to file.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Checks if it is a bye command.
     *
     * @return A boolean: true if it is a bye command.
     */
    public abstract boolean isExit();
}
