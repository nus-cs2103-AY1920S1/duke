package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.MainWindow;
import duke.task.Task;

/**
 * Abstraction of command that adds a task to the list.
 */
abstract class AddTaskCommand extends WritableCommand {
    /** The task to add to the list. */
    Task taskToAdd;

    /**
     * Generic constructor of the command from its arguments.
     *
     * @param commandArgs String array of arguments.
     */
    AddTaskCommand(String[] commandArgs) {
        super(commandArgs);
    }

    /**
     * Adds the task to the task list then displays the verification.
     * Generic implementation of run for any (for now) type of tasks.
     *
     * @param tasks TaskList of tasks to use.
     * @param ui MainWindow to use for displaying command output.
     * @param storage Storage for WritableCommands to execute write-to-disk operations.
     */
    @Override
    void run(TaskList tasks, MainWindow ui, Storage storage) {
        assert taskToAdd != null : "Tried to add null task to task list";
        tasks.addTask(taskToAdd);

        ui.showMessage("Got it. I've added this task:\n"
                + String.format("   %s\n", taskToAdd.getStatusText())
                + String.format("Now you have %d tasks in the list.", tasks.getSize()));
    }
}
