package czkay.duke.logic.command;

import czkay.duke.exception.DukeException;
import czkay.duke.model.task.Task;
import czkay.duke.model.TaskList;
import czkay.duke.storage.Storage;

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
     * Executes the command to delete a task from the task list.
     *
     * @param tasks The task list.
     * @param storage The storage that handles saving and loading the task list.
     * @throws IOException If the I/O operation fails.
     * @throws DukeException If the user input is invalid.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws IOException, DukeException {
        Task deletedTask = tasks.deleteTask(targetIndex);
        tasks.updateReminders();
        storage.update(tasks);
        StringBuilder sb = new StringBuilder("Noted. I've removed this task:\n");
        sb.append(deletedTask.toString());
        sb.append(String.format("\nNow you have %d tasks in the list.", tasks.size()));
        return sb.toString();
    }

}
