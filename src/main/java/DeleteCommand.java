/**
 * Represents a command to remove a task from the arraylist.
 */

public class DeleteCommand extends Command {
    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task taskRemoved = tasks.getList().remove(taskNumber);
        System.out.println("Noted. I've removed this task:");
        ui.printTask(tasks.getList().size(), taskRemoved);
        storage.writeFile(tasks.getList());
    }
}
