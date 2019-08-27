package duke.command;

import duke.DukeUtil;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.EventTask;
import duke.task.TaskUtil;

import java.util.Arrays;

/**
 * Event command that adds a event task to the task list.
 */
class EventCommand extends AddTaskCommand {
    /**
     * Constructor of the Event command.
     * Calls its parent constructor then sets its commandType enum.
     *
     * @param commandArgs String array of arguments.
     */
    public EventCommand(String[] commandArgs) {
        super(commandArgs);
        commandType = Commands.event;
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
        int atIndex = DukeUtil.getIndexOfPattern(commandArgs, "/at");
        if (atIndex == -1) {
            throw new DukeInvalidArgumentException(
                    "Missing /at delimiter for event command",
                    " \u2639 OOPS!!! I dont know what is your event timing!\n"
                            + " You should add a time with\n"
                            + " \'event <description> /at <time>\'");
        }

        String description = DukeUtil.concatStrings(
                Arrays.copyOfRange(commandArgs, 0, atIndex),
                " ");
        String timing = DukeUtil.concatStrings(
                Arrays.copyOfRange(commandArgs, atIndex + 1, commandArgs.length),
                " ");

        TaskUtil.validateTaskDescription(description);

        taskToAdd = new EventTask(description, timing);
    }
}
