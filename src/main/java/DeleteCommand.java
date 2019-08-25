import java.util.ArrayList;

public class DeleteCommand extends Command {
    protected int index;

    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.getTask(index);
        if (index + 1 > tasks.getListSize()) {
            throw new DukeException("\u2639 OOPS!!! This item does not exist.");
        }
        tasks.deleteTask(index);
        int numberOfTasks = tasks.getListSize();
        ui.printDeleteMessage(task, numberOfTasks);
        try {
            storage.writeToHardDisk(tasks);
        } catch (DukeException exception) {
            ui.printException(exception);
        }
    }

    public boolean isExit() {
        return false;
    }
}
