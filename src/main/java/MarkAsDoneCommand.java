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
    public String execute(TaskList tasks, Storage storage) throws IOException, DukeException {
        tasks.markTaskAsDone(targetIndex);
        storage.update(tasks);
        return String.format("Nice! I've marked this task as done:\n%s", tasks.get(targetIndex));
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
