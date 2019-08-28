import java.io.IOException;

/**
 * A Command to add a task to the task list.
 */
public class AddTaskCommand extends Command {
    private Task newTask;

    public AddTaskCommand(Task task) {
        newTask = task;
    }

    /**
     * Returns a boolean value signalling whether the program should exit.
     * @return A boolean value indicating whether the program should exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command to add a task to the task list.
     * @param tasks The task list.
     * @param ui The ui that handles user output.
     * @param storage The storage that handles saving and loading the task list.
     * @throws IOException If the I/O operation fails.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.addTask(newTask);
        storage.update(tasks);
        ui.print("Got it. I've added this task:");
        ui.print(newTask.toString());
        ui.print(String.format("Now you have %d tasks in the list.", tasks.size()));
    }
}
