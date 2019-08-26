package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
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
     * @param ui Ui to use for displaying command output.
     * @param storage Storage for WritableCommands to execute write-to-disk operations.
     */
    @Override
    void run(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(taskToAdd);

        ui.printMsgLine(" Got it. I've added this task:");
        ui.printMsgLine(String.format("   %s", taskToAdd.getStatusText()));
        ui.printMsgLine(String.format(" Now you have %d tasks in the list.", tasks.getSize()));
    }
}
