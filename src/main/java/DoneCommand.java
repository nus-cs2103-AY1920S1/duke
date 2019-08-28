public class DoneCommand extends Command{
    int indexToComplete;

    /**
     * Class constructor.
     * @param indexToComplete Index of Task to be marked as complete
     */
    public DoneCommand(int indexToComplete) {
        super(false);
        this.indexToComplete = indexToComplete;
    }

    /**
     * Execute done command.
     * @param storage Storage used to save tasks into local storage
     * @param tasks TaskList used to store tasks
     * @param ui UI used to interact
     */
    @Override
    public void execute(Storage storage, TaskList tasks, UI ui) throws IndexOutOfBoundsException {
        ui.echoCompletedTask(tasks.completeTask(indexToComplete));
        storage.writeTaskListToFile(tasks);

    }
}
