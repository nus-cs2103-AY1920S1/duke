package duke.command;

import duke.DukeUtil;
import duke.task.TaskList;
import duke.ui.MainWindow;
import duke.storage.Storage;
import duke.task.EventTask;

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
    EventCommand(String[] commandArgs) {
        super(commandArgs);
        commandType = Commands.EVENT;
    }

    /**
     * Checks whether the provided command arguments are valid.
     * Validates that the task description should not be empty,
     * and the timing is of the required format.
     * If valid, it then sets the taskToAdd instance variable,
     * for use in run.
     *
     * @param tasks TaskList of tasks to use.
     * @param ui MainWindow to use for displaying command output.
     * @param storage Storage for WritableCommands to execute write-to-disk operations.
     * @throws DukeInvalidArgumentException If the arguments are invalid as described.
     */
    @Override
    void validate(TaskList tasks, MainWindow ui, Storage storage) throws DukeInvalidArgumentException {
        int atIndex = DukeUtil.getIndexOfPattern(commandArgs, "/at");
        if (atIndex == DukeUtil.FAIL_INDEX) {
            throw new DukeInvalidArgumentException(
                    "Missing /at delimiter for event command",
                    " =X  OOPS!!! I dont know what is your event timing!\n"
                            + " You should add a time with\n"
                            + " \'event <description> /at <time>\'");
        }

        String description = DukeUtil.concatStrings(
                Arrays.copyOfRange(commandArgs, 0, atIndex),
                " ");
        String timing = DukeUtil.concatStrings(
                Arrays.copyOfRange(commandArgs, atIndex + 1, commandArgs.length),
                " ");


        taskToAdd = new EventTask(description, timing);
    }
}
