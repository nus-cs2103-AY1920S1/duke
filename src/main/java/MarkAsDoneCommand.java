import java.io.IOException;

/**
 * A Command to mark as complete a task in the task list.
 */
class MarkAsDoneCommand extends Command {
    private int targetIndex;

    MarkAsDoneCommand(int index) {
        targetIndex = index;
    }

    /**
     * Executes the command to mark as complete a task on the task list.
     *
     * @param tasks The task list.
     * @param storage The storage that handles saving and loading the task list.
     * @throws IOException If the I/O operation fails.
     */
    @Override
    String execute(TaskList tasks, Storage storage) throws IOException, DukeException {
        tasks.markTaskAsDone(targetIndex);
        storage.update(tasks);
        return String.format("Nice! I've marked this task as done:\n%s", tasks.get(targetIndex));
    }

}
