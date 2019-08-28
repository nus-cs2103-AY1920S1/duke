import java.io.IOException;

/**
 * A Command to mark as complete a task in the task list.
 */
public class MarkAsDoneCommand extends Command {
    private int targetIndex;

    public MarkAsDoneCommand(int index) {
        targetIndex = index;
    }

    /**
     * Executes the command to mark as complete a task on the task list.
     *
     * @param tasks The task list.
     * @param ui The ui that handles user output.
     * @param storage The storage that handles saving and loading the task list.
     * @throws IOException If the I/O operation fails.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.markTaskAsDone(targetIndex);
        storage.update(tasks);
        ui.print("Nice! I've marked this task as done:");
        ui.print(tasks.get(targetIndex).toString());
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
}
