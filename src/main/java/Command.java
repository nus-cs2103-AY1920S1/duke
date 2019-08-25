public abstract class Command {
    /**
     * Constructor
     */
    public Command() {

    }

    /**
     * Abstract method to manipulate
     * TaskList/UI/Storage
     *
     * @param tasks Class dealing with manipulating global LinkedList storing tasks
     * @param ui User Interface
     * @param storage Class dealing with local storage of tasks
     * @throws DukeException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

    }

    /**
     * To check if "bye" command is issued
     *
     * @return false except for child ExitCommand
     */
    public boolean isExit() {
        return false;
    }


}
