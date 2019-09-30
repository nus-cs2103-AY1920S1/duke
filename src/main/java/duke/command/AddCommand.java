package duke.command;

import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.exception.DukeException;

import java.io.IOException;

/**
 * Represents the adding of tasks.
 */
public class AddCommand extends Command {

    /** Task object representing task to be added */
    private Task taskToAdd;

    /**
     * Creates an AddCommand object that stores the Task to be added to TaskList
     *
     * @param task Task that is to be added.
     */
    public AddCommand(Task task) {
        this.taskToAdd = task;
    }

    /**
     * Executes the add command on a TaskList
     *
     * @param tasks TaskList on which the Command should be executed on.
     * @throws DukeException If the task failed to be added.
     */
    public String execute(TaskList tasks, Storage storage) throws IOException {
        String result = tasks.addTask(this.taskToAdd);
        storage.save(tasks);
        return result;

    }

}
