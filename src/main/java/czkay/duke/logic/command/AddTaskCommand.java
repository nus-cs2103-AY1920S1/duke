package czkay.duke.logic.command;

import czkay.duke.model.task.Task;
import czkay.duke.model.TaskList;
import czkay.duke.storage.Storage;

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
     * Executes the command to add a task to the task list.
     *
     * @param tasks The task list.
     * @param storage The storage that handles saving and loading the task list.
     * @throws IOException If the I/O operation fails.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws IOException {
        tasks.addTask(newTask);
        tasks.updateReminders();
        storage.update(tasks);
        StringBuilder sb = new StringBuilder("Got it. I've added this task:\n");
        sb.append(newTask.toString());
        sb.append(String.format("\nNow you have %d tasks in the list.", tasks.size()));
        return sb.toString();
    }
}
