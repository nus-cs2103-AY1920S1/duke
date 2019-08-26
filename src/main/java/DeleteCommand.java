import java.io.IOException;

public class DeleteCommand extends Command {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private int taskToBeDeleted;

    public DeleteCommand(int taskNum) {
        this.taskToBeDeleted = taskNum;
    }

    /*
    This method removes the target task from the list and prompts the user the number of remaining tasks saved.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;

        int maxNum = tasks.totalNumTasks();
        if (taskToBeDeleted > maxNum) {
            throw new DukeException("Oops! This task number does not exist.");
        } else {
            Task deleted = tasks.delTask(taskToBeDeleted);
            this.ui.printLine("Noted. I've removed this task:");
            this.ui.printLine(deleted.toString());
            maxNum = tasks.totalNumTasks();
            this.ui.printLine("Now you have " + maxNum + " tasks in the list.");
            storage.save();
        }
    }

    public boolean isExit() {
        return false;
    }
}
