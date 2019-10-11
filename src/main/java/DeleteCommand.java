/**
 * Represents a command to remove a task from the arraylist.
 */

public class DeleteCommand extends Command {
    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task taskRemoved = tasks.getList().remove(taskNumber);
        storage.writeFile(tasks.getList());
        return toString() + ui.printTask(tasks.getList().size(), taskRemoved);
    }

    @Override
    public String toString() {
        return "Noted. I've removed this task:\n";
    }
}
