public abstract class Command {

    /**
     * Used to identify an ExitCommand.
     *
     * @return whether the Command is an ExitCommand.
     */
    abstract boolean isExit();

    /**
     * Executes an Command given TaskList, UI, Storage.
     *
     * @param tasks the TaskList.
     * @param ui the UI.
     * @param storage the file storage.
     */
    abstract void execute(TaskList tasks, Ui ui, Storage storage);

}
