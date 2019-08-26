package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.task.Task;

/**
 * Abstraction of the delete command for deleting a task from the task list.
 */
class DeleteCommand extends WritableCommand {
    /** Index of the task in the list as checked by validate */
    private int taskIndex;

    /**
     * Constructor of the Delete command.
     * Calls its parent constructor then sets its commandType enum.
     *
     * @param commandArgs String array of arguments.
     */
    public DeleteCommand(String[] commandArgs) {
        super(commandArgs);
        commandType = Commands.delete;
    }

    /**
     * Deletes the task from the task list, then saves to disk.
     *
     * @param tasks TaskList of tasks to use.
     * @param ui Ui to use for displaying command output.
     * @param storage Storage for WritableCommands to execute write-to-disk operations.
     */
    @Override
    void run(TaskList tasks, Ui ui, Storage storage) {
        Task taskDeleted = tasks.deleteTaskByIndex(taskIndex);

        ui.printMsgLine(" Noted. I've removed this task:");
        ui.printMsgLine(String.format("   %s", taskDeleted.getStatusText()));
        ui.printMsgLine(String.format(" Now you have %d tasks in the list.", tasks.getSize()));
    }

    /**
     * Checks whether the provided arguments for deleting the task are valid.
     * Verifies the number of arguments and if the task index
     * is valid number within the size of the task list.
     *
     * @param tasks TaskList of tasks to use.
     * @param ui Ui to use for displaying command output.
     * @param storage Storage for WritableCommands to execute write-to-disk operations.
     * @throws DukeInvalidArgumentException If the arguments are invalid as described.
     */
    @Override
    void validate(TaskList tasks, Ui ui, Storage storage) throws DukeInvalidArgumentException {
        if (commandArgs.length > 1) {
            throw new DukeInvalidArgumentException(
                    "Encountered extraneous arguments after delete command",
                    " \u2639 OOPS!!! There shouldn't be so many arguments!");
        }

        try {
            taskIndex = Integer.parseInt(commandArgs[0]);
            tasks.getTaskByIndex(--taskIndex);
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException(
                    "Could not parse argument supplied into a list index",
                    " \u2639 OOPS!!! The task number you gave me wasn't a valid number,\n"
                            + " or you didn't give me one at all!");
        } catch (IndexOutOfBoundsException | NullPointerException ex) {
            throw new DukeInvalidArgumentException(
                    "User number supplied was out of list bounds",
                    " \u2639 OOPS!!! The task number you gave me wasn't within your current list!");
        }
    }
}
