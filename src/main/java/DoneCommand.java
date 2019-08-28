public class DoneCommand extends Command{
    int indexToComplete;

    public DoneCommand(int indexToComplete) {
        super(false);
        this.indexToComplete = indexToComplete;
    }

    @Override
    public void execute(Storage storage, TaskList tasks, UI ui) throws IndexOutOfBoundsException {
        ui.echoCompletedTask(tasks.completeTask(indexToComplete));
        storage.writeTaskListToFile(tasks);

    }

}
