package czkay.duke.logic.command;

import czkay.duke.exception.DukeException;
import czkay.duke.model.TaskList;
import czkay.duke.storage.Storage;

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
     * @param storage The storage that handles saving and loading the task list.
     * @throws IOException If the I/O operation fails.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws IOException, DukeException {
        tasks.markTaskAsDone(targetIndex);
        tasks.updateReminders();
        storage.update(tasks);
        return String.format("Nice! I've marked this task as done:\n%s", tasks.get(targetIndex));
    }

}
