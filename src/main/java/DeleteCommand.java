public class DeleteCommand extends Command{
    int indexToDelete;

    /**
     * Class constructor.
     * @param indexToDelete Index of Task to be deleted
     */
    public DeleteCommand(int indexToDelete) {
        super(false);
        this.indexToDelete = indexToDelete;
    }

    /**
     * Execute delete command.
     * @param storage Storage used to save tasks into local storage
     * @param tasks TaskList used to store tasks
     * @param ui UI used to interact
     */
    @Override
    public void execute(Storage storage, TaskList tasks, UI ui) throws IndexOutOfBoundsException{
        ui.echoDeletedTask(tasks.deleteTask(indexToDelete), tasks.getSize());
        storage.writeTaskListToFile(tasks);
    }

}
