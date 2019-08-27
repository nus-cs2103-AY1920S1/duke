public class DeleteCommand extends Command{
    private int taskNumber;

    public DeleteCommand(String details) {
        super(details);
        this.taskNumber = Integer.valueOf(details);
    }

    public void execute(TaskList tasks, DukeUI ui, StorageData storage) {
        try {
            tasks.delete(this.taskNumber, ui);
            storage.deleteTaskInData(this.taskNumber);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }
}

