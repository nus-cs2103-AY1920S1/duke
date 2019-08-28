public class DeleteCommand extends Command{
    int indexToDelete;

    public DeleteCommand(int indexToDelete) {
        super(false);
        this.indexToDelete = indexToDelete;
    }

    @Override
    public void execute(Storage storage, TaskList tasks, UI ui) throws IndexOutOfBoundsException{
        ui.echoDeletedTask(tasks.deleteTask(indexToDelete), tasks.getSize());
        storage.writeTaskListToFile(tasks);
    }

}
