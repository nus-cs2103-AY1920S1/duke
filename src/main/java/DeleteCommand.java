import java.util.List;

public class DeleteCommand extends Command {
    private int taskId;

    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String str;
        if (taskId > tasks.getSize()) {
            throw new DukeException("Please choose a task within the list");
        } else {
            Task toDelete = tasks.deleteTask(taskId - 1);
            str = "Noted. I've removed this task:\n" + " " + toDelete.toString() + "\nNow you have " + tasks.getSize()
                    + " tasks in the list.";
            ui.addBorder(str);
        }
        storage.writeToFile();
    }
}
