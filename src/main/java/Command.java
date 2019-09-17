public abstract class Command {
    /**
     * Constructor.
     */
    public Command() {

    }

    /**
     * Abstract method to manipulate TaskList/UI/Storage.
     *
     * @param tasks Class dealing with manipulating global LinkedList storing tasks
     * @param ui User Interface
     * @param storage Class dealing with local storage of tasks
     * @return updated task as a string
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;


}
