package duke.command;

import duke.DukeUtil;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.DeadlineTask;
import duke.task.TaskUtil;

import java.util.Arrays;

/**
 * Deadline command that adds a deadline task to the task list.
 */
class DeadlineCommand extends AddTaskCommand {
    /**
     * Constructor of the Deadline command.
     * Calls its parent constructor then sets its commandType enum.
     *
     * @param commandArgs String array of arguments.
     */
    public DeadlineCommand(String[] commandArgs) {
        super(commandArgs);
        commandType = Commands.deadline;
    }

    /**
     * Checks whether the provided command arguments are valid.
     * Validates that the task description should not be empty,
     * and the timing is of the required format.
     * If valid, it then sets the taskToAdd instance variable,
     * for use in run.
     *
     * @param tasks TaskList of tasks to use.
     * @param ui Ui to use for displaying command output.
     * @param storage Storage for WritableCommands to execute write-to-disk operations.
     * @throws DukeInvalidArgumentException If the arguments are invalid as described.
     */
    @Override
    void validate(TaskList tasks, Ui ui, Storage storage) throws DukeInvalidArgumentException {
        int byIndex = DukeUtil.getIndexOfPattern(commandArgs, "/by");
        if (byIndex == -1) {
            throw new DukeInvalidArgumentException(
                    "Missing /by delimiter for deadline command",
                    " \u2639 OOPS!!! I dont know what is your deadline!\n"
                            + " You should add a deadline with\n"
                            + " \'deadline <description> /by <timing>\'");
        }

        String description = DukeUtil.concatStrings(
                Arrays.copyOfRange(commandArgs, 0, byIndex),
                " ");
        String timing = DukeUtil.concatStrings(
                Arrays.copyOfRange(commandArgs, byIndex + 1, commandArgs.length),
                " ");

        TaskUtil.validateTaskDescription(description);

        taskToAdd = new DeadlineTask(description, timing);
    }
}
