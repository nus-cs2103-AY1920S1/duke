package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command to complete a task.
 */
public class DoneCommand extends Command {
    /** Index of task user has completed. */
    String userIn;

    private static final String MESSAGE_SUCCESS = "Nice! I've marked this task as done: \n" + "%s";

    private static final String MESSAGE_INVALID_NUM = "Invalid task number entered";

    /**
     * Initializes a DoneCommand with the user input.
     * @param userIn index of task user has completed.
     */
    public DoneCommand(String userIn) {
        this.userIn = userIn.trim();
    }

    /**
     * Completes a specified task by marking it as done.
     * @param tasks TaskList object which represents the list of tasks created by the user.
     * @param storage storage which loads, saves and stores data of user.
     * @return CommandResult result of executing the command.
     * @throws DukeException if index in user input is invalid.
     */
    public CommandResult execute(TaskList tasks, Storage storage) throws DukeException {
        try {
            int taskInd = Integer.parseInt(userIn) - 1;
            Task currTask = tasks.getTask(taskInd);
            currTask.markAsDone();
            storage.saveAll(tasks);
            return new CommandResult(String.format(MESSAGE_SUCCESS, currTask));
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException(MESSAGE_INVALID_NUM);
        }
    }
}
