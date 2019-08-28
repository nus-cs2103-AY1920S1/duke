import java.io.IOException;

/**
 * A Command to delete a task from the task list.
 */
public class DeleteTaskCommand extends Command {
    private int targetIndex;

    public DeleteTaskCommand(int index) {
        targetIndex = index;
    }

    /**
     * Returns a boolean value signalling whether the program should exit.
     *
     * @return A boolean value indicating whether the program should exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command to delete a task from the task list.
     *
     * @param tasks The task list.
     * @param ui The ui that handles user output.
     * @param storage The storage that handles saving and loading the task list.
     * @throws IOException If the I/O operation fails.
     * @throws DukeException If the user input is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        Task deletedTask = tasks.deleteTask(targetIndex);
        storage.update(tasks);
        ui.print("Noted. I've removed this task:");
        ui.print(deletedTask.toString());
        ui.print(String.format("Now you have %d tasks in the list.", tasks.size()));
    }
}
